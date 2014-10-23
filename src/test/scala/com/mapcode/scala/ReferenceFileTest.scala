/*
 * Copyright (C) 2014 Stichting Mapcode Foundation (http://www.mapcode.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.mapcode.scala

import java.io.{BufferedReader, EOFException, InputStream, InputStreamReader}

import org.scalatest.{FunSuite, Matchers}

import scala.collection.mutable.ArrayBuffer
import scala.util.Try

object ReferenceFileTest extends Matchers {
  val RANDOM_REFERENCE_FILE_1: String = "/random_1k.txt"
  val RANDOM_REFERENCE_FILE_2: String = "/random_10k.txt"
  val RANDOM_REFERENCE_FILE_3: String = "/random_100k.txt"
  val RANDOM_REFERENCE_FILE_1_HP: String = "/random_hp_1k.txt"
  val RANDOM_REFERENCE_FILE_2_HP: String = "/random_hp_10k.txt"
  val RANDOM_REFERENCE_FILE_3_HP: String = "/random_hp_100k.txt"
  val GRID_REFERENCE_FILE_1: String = "/grid_1k.txt"
  val GRID_REFERENCE_FILE_2: String = "/grid_10k.txt"
  val GRID_REFERENCE_FILE_3: String = "/grid_100k.txt"
  val GRID_REFERENCE_FILE_1_HP: String = "/grid_hp_1k.txt"
  val GRID_REFERENCE_FILE_2_HP: String = "/grid_hp_10k.txt"
  val GRID_REFERENCE_FILE_3_HP: String = "/grid_hp_100k.txt"
  val BOUNDARIES_REFERENCE_FILE: String = "/boundaries.txt"
  val BOUNDARIES_REFERENCE_FILE_HP: String = "/boundaries_hp.txt"
  val LOG_LINE_EVERY: Int = 25000
  val METERS_DELTA: Double = 10.0

  private def getNextReferenceRecord(chunkedFile: ChunkedFile): ReferenceFileTest.ReferenceRec = {
    val firstLine: String = chunkedFile.readNonEmptyLine()
    val args: Array[String] = firstLine.split(" ")
    assert((args.length == 3) || (args.length == 6),
      "Expecting 3 or 6 elements, not " + args.length + " in line: " + firstLine)
    val count: Int = Integer.parseInt(args(0))
    assert((1 <= count) && (count <= 21), "Expecting between 1 and 21 mapcodes")
    val (latDeg, lonDeg) = (args(1).toDouble, args(2).toDouble)
    val point: Point = Point.fromMicroDeg(Point.degToMicroDeg(latDeg), Point.degToMicroDeg(lonDeg))
    assert((-90 <= point.latDeg) && (point.latDeg <= 90), "Latitude must be in [-90, 90]")
    assert((-180 <= point.lonDeg) && (point.lonDeg <= 180), "Longitude must be in [-180, 180]")
    val mapcodeRecs = ArrayBuffer[ReferenceFileTest.MapcodeRec]()

    {
      var i: Int = 0
      while (i < count) {
        {
          val line: String = chunkedFile.readNonEmptyLine()
          assert(!line.isEmpty, "Line should not be empty")
          val mapcodeLine: Array[String] = line.split(" ")
          mapcodeLine.length should be(2)
          val territory = Territory.fromString(mapcodeLine(0))
          val mapcode: String = mapcodeLine(1)
          val mapcodeRec: ReferenceFileTest.MapcodeRec = new ReferenceFileTest.MapcodeRec(mapcode, territory)
          mapcodeRecs += mapcodeRec
        }
        i += 1
      }
    }
    mapcodeRecs.size should be(count)
    ReferenceRec(point, mapcodeRecs)
  }

  /**
   * Utility class to read chunked files. Chunked files have extension appended to them
   * like '.a', '.b', etc. This class provides reading lines from such files and moving
   * to next chunks when needed.
   */
  class ChunkedFile(val baseFileName: String) {
    var fileExt: Char = 'a'
    def fileName = baseFileName + '.' + fileExt
    var inputStream: InputStream = getClass.getResourceAsStream(fileName)
    var bufferedReader = new BufferedReader(new InputStreamReader(inputStream))

    def readNonEmptyLine(): String = {
      var line: String = null
      do {
        var tryNextChunk: Boolean = false
        try {
          line = bufferedReader.readLine
          tryNextChunk = !bufferedReader.ready || (line == null)
        }
        catch {
          case ignored: EOFException => tryNextChunk = true
        }
        if (line == null) {
          line = ""
        }
        if (tryNextChunk) {
          nextChunk()
        }
      } while (line.isEmpty)
      line
    }

    def nextChunk() {
      close()
      fileExt = (fileExt.toInt + 1).toChar
      inputStream = getClass.getResourceAsStream(fileName)
      if (inputStream != null) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
      }
      else {
        throw new EOFException
      }
    }

    def close() {
      Try(bufferedReader.close())
      Try(inputStream.close())
      bufferedReader = null
      inputStream = null
    }
  }

  case class ReferenceRec(point: Point, mapcodes: Seq[MapcodeRec])

  case class MapcodeRec(mapcode: String, territory: Option[Territory.Territory])


}

class ReferenceFileTest extends FunSuite with Matchers {

  import com.mapcode.scala.ReferenceFileTest._

  test(" checkRandomReferenceRecords") {
    checkFile(RANDOM_REFERENCE_FILE_1)
    checkFile(RANDOM_REFERENCE_FILE_2)
    checkFile(RANDOM_REFERENCE_FILE_3)
  }

  test("checkGridReferenceRecords") {
    checkFile(GRID_REFERENCE_FILE_1)
    checkFile(GRID_REFERENCE_FILE_2)
    checkFile(GRID_REFERENCE_FILE_3)
  }

  test("checkBoundariesReferenceRecords") {
    checkFile(BOUNDARIES_REFERENCE_FILE)
  }

  test("checkRandomReferenceRecordsPrecision2") {
    checkFile(RANDOM_REFERENCE_FILE_1_HP)
    checkFile(RANDOM_REFERENCE_FILE_2_HP)
    checkFile(RANDOM_REFERENCE_FILE_3_HP)
  }

  test("checkGridReferenceRecordsPrecision2") {
    checkFile(GRID_REFERENCE_FILE_1_HP)
    checkFile(GRID_REFERENCE_FILE_2_HP)
    checkFile(GRID_REFERENCE_FILE_3_HP)
  }

  test("checkBoundariesReferenceRecordsPrecision2") {
    checkFile(BOUNDARIES_REFERENCE_FILE_HP)
  }

  private def checkFile(baseFileName: String) {
    var error: Int = 0
    var maxdelta: Double = 0
    val chunkedFile: ChunkedFile = new ChunkedFile(baseFileName)
    try {
      var i: Int = 1
      while (true) {
        val reference: ReferenceFileTest.ReferenceRec = getNextReferenceRecord(chunkedFile)
        val showLogLine: Boolean = (i % LOG_LINE_EVERY) == 0
        if (showLogLine) {
          println(s"checkFile: #$i, file=${chunkedFile.fileName}")
          println(s"checkFile: lat/lon  = ${reference.point}", reference.point)
          println(s"checkFile: expected = ${reference.mapcodes}")
        }
        val results = MapcodeCodec.encode(reference.point.latDeg, reference.point.lonDeg)
        if (showLogLine) {
          println(s"checkFile: actual   = $results")
        }
        for (result <- results) {
          var found: Boolean = false
          if (!found) {
            for (referenceMapcodeRec <- reference.mapcodes) {
              if (referenceMapcodeRec.territory == Some(result.territory)) {
                if (referenceMapcodeRec.mapcode.lastIndexOf('-') > 4) {
                  if (referenceMapcodeRec.mapcode == result.mapcodePrecision2) {
                    found = true
                  }
                }
                else {
                  if (referenceMapcodeRec.mapcode == result.mapcode) {
                    found = true
                  }
                }
              }
            }
          }
          if (!found) {
            println("checkFile: Mapcode '$result' at ${reference.point} is not in the reference file!")
            error += 1
          }
        }
        for (referenceMapcodeRec <- reference.mapcodes) {
          var found: Boolean = false
          for (result <- results) {
            if (!found) {
              if (referenceMapcodeRec.territory == Some(result.territory)) {
                if (referenceMapcodeRec.mapcode.lastIndexOf('-') > 4) {
                  if (referenceMapcodeRec.mapcode == result.mapcodePrecision2) {
                    found = true
                  }
                }
                else {
                  if (referenceMapcodeRec.mapcode == result.mapcode) {
                    found = true
                  }
                }
              }
            }
          }
          if (!found) {
            println(s"checkFile: Mapcode '${referenceMapcodeRec.territory} ${referenceMapcodeRec.mapcode}' " +
              s"at ${reference.point} is not produced by the decoder!")
            error += 1
          }
        }
        for (mapcodeRec <- reference.mapcodes) {
          try {
            val result = MapcodeCodec.decode(mapcodeRec.mapcode, mapcodeRec.territory.get)
            val distanceMeters: Double = reference.point.distanceInMeters(result)
            maxdelta = Math.max(maxdelta, distanceMeters)
            if (distanceMeters > METERS_DELTA) {
              println(s"Mapcode ${mapcodeRec.territory} ${mapcodeRec.mapcode} was generated " +
                s"for point ${reference.point}, but decodes to point $result which is $distanceMeters " +
                s"meters from the original point.")
              error += 1
            }
          }
          catch {
            case unknownMapcodeException: UnknownMapcodeException =>
              println(s"Mapcode ${mapcodeRec.territory} ${mapcodeRec.mapcode} was generated for " +
                s"point ${reference.point}, but cannot be decoded.")
              error += 1
          }
        }
        if (showLogLine) {
          println()
        }
        i += 1
      }
    }
    catch {
      case e: EOFException => // ok
    }
    finally {
      chunkedFile.close()
    }
    println(s"checkFile: Maximum delta for this testset = $maxdelta")
    error should be(0)
  }
}

