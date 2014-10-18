package com.mapcode.scala

import org.scalacheck.Gen
import org.scalatest.prop.GeneratorDrivenPropertyChecks
import org.scalatest.{FunSuite, Matchers}

import scala.util.{Failure, Success, Try}

class CommonTest extends FunSuite with Matchers with GeneratorDrivenPropertyChecks {
  test("xDivider implementation matches") {
    an[IllegalArgumentException] should be thrownBy Common.xDivider((0xAB << 19) + 1, 0)
    an[IllegalArgumentException] should be thrownBy Common.xDivider(0, -(0xAB << 19) - 1)
    Common.xDivider(1, 2147483647) should equal(360)
    Common.xDivider(-2147483648, -1) should equal(360)
    Common.xDivider(0, -1) should equal(360)
    Common.xDivider(-738253558, 0) should equal(360)
    Common.xDivider(0, -1) should equal(360)
    Common.xDivider(-2147483648, 1) should equal(360)
    Common.xDivider(-1981120568, 1395750030) should equal(360)
    Common.xDivider(0, -1) should equal(360)
    Common.xDivider(-1, 0) should equal(360)
    Common.xDivider(-12688310, 0) should equal(360)
    Common.xDivider(1, -1) should equal(360)
    Common.xDivider(-923789050, 1843051460) should equal(360)
    Common.xDivider(-979973608, 1) should equal(360)
    Common.xDivider(35295358, 1) should equal(440)
    Common.xDivider(-764898486, 339088950) should equal(360)
    Common.xDivider(-1, 2147483647) should equal(360)
    Common.xDivider(-2147483648, 280681133) should equal(360)
    Common.xDivider(-1337224547, 2147483647) should equal(360)
    Common.xDivider(11747764, 2091616211) should equal(367)
    Common.xDivider(0, 1110280926) should equal(360)
    Common.xDivider(0, -1) should equal(360)
    Common.xDivider(-2147483648, 1) should equal(360)
    Common.xDivider(-347932293, 189206263) should equal(360)
    Common.xDivider(-105347201, 1) should equal(360)
    Common.xDivider(1, 2147483647) should equal(360)
    Common.xDivider(-1, 1298860505) should equal(360)
    Common.xDivider(-2147483648, 0) should equal(360)
    Common.xDivider(-2147483648, 2147483647) should equal(360)
    Common.xDivider(-1, 953792823) should equal(360)
    Common.xDivider(0, -1) should equal(360)
    Common.xDivider(-1, 1) should equal(360)
    Common.xDivider(-2147483648, 0) should equal(360)
    Common.xDivider(-1, 719996021) should equal(360)
    Common.xDivider(-624299070, 1549506786) should equal(360)
    Common.xDivider(-2147483648, 1939772766) should equal(360)
    Common.xDivider(-1352286747, 0) should equal(360)
    Common.xDivider(-1, 0) should equal(360)
    Common.xDivider(-2147483648, 530811657) should equal(360)
    Common.xDivider(0, 0) should equal(360)
    Common.xDivider(-1418558120, 0) should equal(360)
    Common.xDivider(-329466085, 1114252615) should equal(360)
    Common.xDivider(1, 2147483647) should equal(360)
    Common.xDivider(-610187002, 938528128) should equal(360)
    Common.xDivider(-2147483648, 2147483647) should equal(360)
    Common.xDivider(-1, 1091925580) should equal(360)
    Common.xDivider(0, 1) should equal(360)
    Common.xDivider(1, 0) should equal(360)
    Common.xDivider(-789754122, 0) should equal(360)
    Common.xDivider(-1, -34318670) should equal(435)
    Common.xDivider(-1, 1073679700) should equal(360)
    Common.xDivider(-203546789, 928200345) should equal(360)
    Common.xDivider(-1, 705484536) should equal(360)
    Common.xDivider(-1, 1) should equal(360)
    Common.xDivider(-1, 1) should equal(360)
    Common.xDivider(-1, 2147483647) should equal(360)
    Common.xDivider(1, 1961688364) should equal(360)
    Common.xDivider(0, 1) should equal(360)
    Common.xDivider(-1, 0) should equal(360)
    Common.xDivider(-2147483648, 1894394614) should equal(360)
    Common.xDivider(-2147483648, 1) should equal(360)
    Common.xDivider(-2147483648, 2147483647) should equal(360)
    Common.xDivider(-1, 908865021) should equal(360)
    Common.xDivider(-636322092, -1) should equal(360)
    Common.xDivider(-1, 0) should equal(360)
    Common.xDivider(0, 2147483647) should equal(360)
    Common.xDivider(-1486281741, -1) should equal(360)
    Common.xDivider(1, 2147483647) should equal(360)
    Common.xDivider(-1, 1839038045) should equal(360)
    Common.xDivider(1, 2144392601) should equal(360)
    Common.xDivider(1, 912820638) should equal(360)
    Common.xDivider(-1, -12088689) should equal(368)
    Common.xDivider(-2147483648, 92208593) should equal(360)
    Common.xDivider(1, -1) should equal(360)
    Common.xDivider(-1494306368, 2147483647) should equal(360)
    Common.xDivider(1, 0) should equal(360)
    Common.xDivider(-1, 1) should equal(360)
    Common.xDivider(-938197723, 1039531612) should equal(360)
    Common.xDivider(-317147171, 1) should equal(360)
    Common.xDivider(16175458, 1) should equal(374)
    Common.xDivider(1, -1) should equal(360)
    Common.xDivider(-1490941538, -70826382) should equal(1094)
    Common.xDivider(1, 0) should equal(360)
    Common.xDivider(-7358662, 315473186) should equal(360)
    Common.xDivider(-2147483648, 2147483647) should equal(360)
    Common.xDivider(-867674273, -1) should equal(360)
    Common.xDivider(-2147483648, 1) should equal(360)
    Common.xDivider(0, 2147483647) should equal(360)
    Common.xDivider(0, 1) should equal(360)
    Common.xDivider(-733520913, 1650335406) should equal(360)
    Common.xDivider(1, 676455115) should equal(360)
    Common.xDivider(-1, 2084295462) should equal(360)
    Common.xDivider(0, 1) should equal(360)
    Common.xDivider(-381979709, 1) should equal(360)
    Common.xDivider(0, 1) should equal(360)
    Common.xDivider(-839396328, 2147483647) should equal(360)
    Common.xDivider(-1886631735, 0) should equal(360)
    Common.xDivider(1, 1) should equal(360)
    Common.xDivider(-657428734, 1) should equal(360)
    Common.xDivider(0, 0) should equal(360)
    Common.xDivider(-1, 1) should equal(360)
  }

  implicit override val generatorDrivenConfig = PropertyCheckConfig(minSuccessful = 10000000)
  val codexGenerator = Gen.choose(0, DataAccess.FILE_DATA.length / 20 - 1)
  test("countCityCoordinatesForCountry") {
    Common.countCityCoordinatesForCountry(22, 5088, 4096) should equal(38)
    Common.countCityCoordinatesForCountry(22, 13643, 5116) should equal(5)
    Common.countCityCoordinatesForCountry(22, 11031, 3170) should equal(25)
    Common.countCityCoordinatesForCountry(13, 11118, 3257) should equal(2)
    Common.countCityCoordinatesForCountry(22, 6529, 2129) should equal(25)
    Common.countCityCoordinatesForCountry(13, 15899, 6295) should equal(2)
    Common.countCityCoordinatesForCountry(22, 12371, 5200) should equal(3)
    Common.countCityCoordinatesForCountry(22, 7209, 1595) should equal(54)
    Common.countCityCoordinatesForCountry(22, 14691, 14165) should equal(13)
    Common.countCityCoordinatesForCountry(22, 9191, 3172) should equal(40)
    Common.countCityCoordinatesForCountry(22, 8773, 7115) should equal(40)
    Common.countCityCoordinatesForCountry(22, 13416, 1115) should equal(6)
    Common.countCityCoordinatesForCountry(22, 2485, 1207) should equal(38)
    Common.countCityCoordinatesForCountry(22, 8407, 1035) should equal(29)
    Common.countCityCoordinatesForCountry(22, 3006, 1818) should equal(28)
    Common.countCityCoordinatesForCountry(22, 6760, 5565) should equal(36)
    Common.countCityCoordinatesForCountry(22, 8982, 5425) should equal(43)
    Common.countCityCoordinatesForCountry(22, 7803, 1813) should equal(40)
    Common.countCityCoordinatesForCountry(22, 9809, 8892) should equal(9)
    Common.countCityCoordinatesForCountry(22, 13565, 3304) should equal(9)
    Common.countCityCoordinatesForCountry(22, 14109, 9389) should equal(8)
    Common.countCityCoordinatesForCountry(22, 9563, 3675) should equal(3)
    Common.countCityCoordinatesForCountry(22, 11859, 6481) should equal(10)
    Common.countCityCoordinatesForCountry(22, 7379, 1461) should equal(32)
    Common.countCityCoordinatesForCountry(22, 7242, 3825) should equal(54)
    Common.countCityCoordinatesForCountry(22, 8410, 5573) should equal(29)
    Common.countCityCoordinatesForCountry(22, 15595, 2801) should equal(16)
    Common.countCityCoordinatesForCountry(22, 8274, 6874) should equal(44)
    Common.countCityCoordinatesForCountry(22, 11862, 2747) should equal(10)
    Common.countCityCoordinatesForCountry(22, 11308, 5999) should equal(37)
    Common.countCityCoordinatesForCountry(22, 16062, 1661) should equal(12)
    Common.countCityCoordinatesForCountry(22, 6034, 2646) should equal(29)
    Common.countCityCoordinatesForCountry(22, 13104, 10731) should equal(12)
    Common.countCityCoordinatesForCountry(22, 15609, 724) should equal(16)
    Common.countCityCoordinatesForCountry(22, 12566, 10724) should equal(6)
    Common.countCityCoordinatesForCountry(22, 2407, 952) should equal(36)
    Common.countCityCoordinatesForCountry(22, 10800, 5554) should equal(6)
    Common.countCityCoordinatesForCountry(22, 9851, 8454) should equal(28)
    Common.countCityCoordinatesForCountry(22, 6354, 3387) should equal(23)
    Common.countCityCoordinatesForCountry(22, 8910, 373) should equal(31)
    Common.countCityCoordinatesForCountry(22, 15364, 6092) should equal(6)
    Common.countCityCoordinatesForCountry(22, 12689, 12575) should equal(4)
    Common.countCityCoordinatesForCountry(13, 15542, 8007) should equal(2)
    Common.countCityCoordinatesForCountry(22, 8696, 7465) should equal(37)
    Common.countCityCoordinatesForCountry(22, 9226, 5884) should equal(39)
    Common.countCityCoordinatesForCountry(13, 15541, 13067) should equal(2)
    Common.countCityCoordinatesForCountry(22, 11313, 5852) should equal(37)
    Common.countCityCoordinatesForCountry(22, 15031, 11507) should equal(9)
    Common.countCityCoordinatesForCountry(22, 10280, 6680) should equal(88)
    Common.countCityCoordinatesForCountry(22, 14571, 7751) should equal(4)
    Common.countCityCoordinatesForCountry(22, 12479, 2517) should equal(5)
    Common.countCityCoordinatesForCountry(22, 11581, 4544) should equal(34)
    Common.countCityCoordinatesForCountry(22, 10714, 790) should equal(32)
    Common.countCityCoordinatesForCountry(22, 5539, 2753) should equal(44)
    Common.countCityCoordinatesForCountry(22, 12430, 3858) should equal(11)
    Common.countCityCoordinatesForCountry(22, 3422, 2894) should equal(29)
    Common.countCityCoordinatesForCountry(22, 13036, 10147) should equal(4)
    Common.countCityCoordinatesForCountry(22, 12074, 9428) should equal(5)
    Common.countCityCoordinatesForCountry(22, 15268, 6288) should equal(6)
    Common.countCityCoordinatesForCountry(22, 15768, 8736) should equal(14)
    Common.countCityCoordinatesForCountry(22, 8435, 1489) should equal(29)
    Common.countCityCoordinatesForCountry(22, 9604, 8890) should equal(4)
    Common.countCityCoordinatesForCountry(22, 6580, 2617) should equal(42)
    Common.countCityCoordinatesForCountry(22, 8104, 268) should equal(48)
    Common.countCityCoordinatesForCountry(22, 15432, 7001) should equal(8)
    Common.countCityCoordinatesForCountry(22, 15324, 10003) should equal(6)
    Common.countCityCoordinatesForCountry(13, 7567, 3625) should equal(2)
    Common.countCityCoordinatesForCountry(22, 10600, 6625) should equal(12)
    Common.countCityCoordinatesForCountry(22, 16056, 12250) should equal(12)
    Common.countCityCoordinatesForCountry(22, 11392, 724) should equal(43)
    Common.countCityCoordinatesForCountry(22, 13942, 4233) should equal(33)
    Common.countCityCoordinatesForCountry(22, 14917, 14784) should equal(12)
    Common.countCityCoordinatesForCountry(22, 15511, 11371) should equal(15)
    Common.countCityCoordinatesForCountry(22, 3130, 2961) should equal(41)
    Common.countCityCoordinatesForCountry(22, 9844, 4740) should equal(28)
    Common.countCityCoordinatesForCountry(22, 13035, 509) should equal(4)
    Common.countCityCoordinatesForCountry(22, 15928, 5370) should equal(10)
    Common.countCityCoordinatesForCountry(22, 5152, 5098) should equal(15)
    Common.countCityCoordinatesForCountry(22, 7573, 82) should equal(30)
    Common.countCityCoordinatesForCountry(22, 5664, 1116) should equal(40)
    Common.countCityCoordinatesForCountry(22, 6813, 6687) should equal(41)
    Common.countCityCoordinatesForCountry(22, 10615, 3984) should equal(18)
    Common.countCityCoordinatesForCountry(22, 14772, 1711) should equal(8)
    Common.countCityCoordinatesForCountry(22, 6191, 595) should equal(41)
    Common.countCityCoordinatesForCountry(22, 5301, 3881) should equal(27)
    Common.countCityCoordinatesForCountry(22, 2740, 365) should equal(49)
    Common.countCityCoordinatesForCountry(22, 9631, 62) should equal(7)
    Common.countCityCoordinatesForCountry(22, 14115, 4861) should equal(8)
    Common.countCityCoordinatesForCountry(22, 14596, 257) should equal(8)
    Common.countCityCoordinatesForCountry(22, 9813, 2747) should equal(9)
    Common.countCityCoordinatesForCountry(22, 10481, 8974) should equal(8)
    Common.countCityCoordinatesForCountry(22, 13657, 3926) should equal(27)
    Common.countCityCoordinatesForCountry(22, 15797, 13445) should equal(18)
    Common.countCityCoordinatesForCountry(22, 14665, 11311) should equal(5)
    Common.countCityCoordinatesForCountry(22, 8419, 4698) should equal(29)
    Common.countCityCoordinatesForCountry(22, 1532, 908) should equal(52)
    Common.countCityCoordinatesForCountry(22, 14713, 11355) should equal(11)
    Common.countCityCoordinatesForCountry(22, 15639, 12830) should equal(10)
    Common.countCityCoordinatesForCountry(22, 8697, 643) should equal(37)
    Common.countCityCoordinatesForCountry(22, 14865, 5846) should equal(10)
    Common.countCityCoordinatesForCountry(22, 15650, 10512) should equal(9)
    Common.countCityCoordinatesForCountry(22, 10251, 7830) should equal(88)
    Common.countCityCoordinatesForCountry(22, 15958, 15217) should equal(11)
    Common.countCityCoordinatesForCountry(22, 15165, 8865) should equal(8)
    Common.countCityCoordinatesForCountry(22, 14680, 1122) should equal(13)
    Common.countCityCoordinatesForCountry(22, 6817, 6158) should equal(41)
    Common.countCityCoordinatesForCountry(22, 14291, 3140) should equal(2)
    Common.countCityCoordinatesForCountry(22, 15201, 12196) should equal(7)
    Common.countCityCoordinatesForCountry(22, 14465, 13504) should equal(4)
    Common.countCityCoordinatesForCountry(22, 11225, 1158) should equal(25)
    Common.countCityCoordinatesForCountry(21, 10681, 6222) should equal(1)
    Common.countCityCoordinatesForCountry(22, 11502, 4025) should equal(40)
    Common.countCityCoordinatesForCountry(22, 10569, 8531) should equal(21)
    Common.countCityCoordinatesForCountry(22, 7733, 7295) should equal(46)
    Common.countCityCoordinatesForCountry(13, 12564, 2568) should equal(1)
    Common.countCityCoordinatesForCountry(22, 15860, 4470) should equal(10)
    Common.countCityCoordinatesForCountry(22, 8249, 3764) should equal(44)
    Common.countCityCoordinatesForCountry(22, 9768, 6331) should equal(3)
    Common.countCityCoordinatesForCountry(22, 15430, 737) should equal(8)
    Common.countCityCoordinatesForCountry(22, 15324, 8866) should equal(6)
    Common.countCityCoordinatesForCountry(22, 15669, 12595) should equal(13)
    Common.countCityCoordinatesForCountry(22, 2056, 667) should equal(22)
    Common.countCityCoordinatesForCountry(22, 10270, 3740) should equal(88)
    Common.countCityCoordinatesForCountry(22, 14615, 2342) should equal(4)
    Common.countCityCoordinatesForCountry(22, 5411, 1741) should equal(42)
    Common.countCityCoordinatesForCountry(22, 3101, 2432) should equal(41)
    Common.countCityCoordinatesForCountry(22, 13996, 6449) should equal(30)
    Common.countCityCoordinatesForCountry(22, 14573, 11871) should equal(4)
    Common.countCityCoordinatesForCountry(22, 14582, 2362) should equal(6)
    Common.countCityCoordinatesForCountry(22, 5832, 2313) should equal(38)
    Common.countCityCoordinatesForCountry(22, 15484, 7958) should equal(9)
    Common.countCityCoordinatesForCountry(22, 11676, 3439) should equal(15)
    Common.countCityCoordinatesForCountry(22, 15018, 1786) should equal(6)
    Common.countCityCoordinatesForCountry(22, 15723, 577) should equal(9)
    Common.countCityCoordinatesForCountry(22, 8684, 1123) should equal(37)
    Common.countCityCoordinatesForCountry(22, 11746, 1450) should equal(36)
    Common.countCityCoordinatesForCountry(22, 4552, 4259) should equal(27)
    Common.countCityCoordinatesForCountry(22, 6304, 4328) should equal(27)
    Common.countCityCoordinatesForCountry(22, 11309, 8680) should equal(37)
    Common.countCityCoordinatesForCountry(22, 8845, 4763) should equal(38)
    Common.countCityCoordinatesForCountry(22, 15191, 4681) should equal(6)
    Common.countCityCoordinatesForCountry(22, 14776, 11995) should equal(8)
    Common.countCityCoordinatesForCountry(22, 9953, 7481) should equal(47)
    Common.countCityCoordinatesForCountry(22, 13542, 4447) should equal(6)
    Common.countCityCoordinatesForCountry(22, 9847, 6561) should equal(28)
  }
}