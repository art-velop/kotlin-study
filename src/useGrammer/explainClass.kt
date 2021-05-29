package useGrammer

class Person constructor(name: String, gender: String = "man") {
  // 코틀린에서는 프로퍼티에 디폴트 getter/setter가 포함이 되어있기 때문에 따로 만들 필요가 없다. getter/setter의 동작을 커스터마이징 하고 싶다면 별도로 정의를 해야 한다.
  var name: String = ""
    get() {

      println("GET_이름 : ${field}")

      return field
    }
    set(value) {
      println("SET_이름 : ${field} -> ${value}")
      field = value
    }

  var gender: String =""

  // lateinit으로 선언한 경우에는 클래스안에서 바로 초기화하지 않아도 된다
  // lateinit은 var프로퍼티에만 붙일 수 있음
  lateinit var birthDay: String;

  init {
    this.name = name
    this.gender = gender
  }

  fun print() {
    // 코틀린은 삼항연산자가 없지만 표현식을 다음과 같이 사용이 가능하다
    println("이름 : ${this.name}")
    println("성별 : ${if (this.gender.equals("man")) "남자" else "여자"}")
  }
}

// init과 constructor의 차이
class Car(val name: String, val price: Int) {

  var age: Int = 0

  init {
    println("${this.price}짜리 ${this.name} 기본 생성자 실행")
  }

  // 보조생성자가 this 키워드를 사용하여 기본생성자를 호출함
  constructor(name: String): this(name, 100000) {
    println("${this.price}짜리 ${this.name} 보조 생성자 실행")
  }

}

//클래스 없이 object 표현식을 사용해 상속을 할 수 있다. 이때의 상속은 1회용이 된다.
open class Order constructor(orderName: String, orderNum: Int) {
  private val orderName: String = orderName
  private val orderNum: Int = orderNum

  open fun print() {
    println("주문이름 : ${this.orderName}")
    println("주문번호 : ${this.orderNum}")
  }

}

// 동반자 객체(Compainon Object)
// 어떤 클래스의 모든 인스턴스가 공유하는 객체를 만들고 싶을 때 사용한다.
// 코틀린에는 static이 키워드가 존재하지 않는다 static과 같은 효과를 내려면 다음과 같이 사용해야 한다
class Color private constructor() {
  companion object{
    val black: String = "#000000"
    val white: String = "#ffffff"

    fun colorInfoPrint () {
      println("BLACK :: ${this.black}")
      println("WHITE :: ${this.white}")
    }
  }
}

class Outer constructor(playerName: String) {
  val playerName: String

  init {
    this.playerName = playerName
  }

  // 자식 class는 부모 class의 맴버함수나 프로퍼티에 접근이 불가능하다
  class Jacket() {
    init {
      println("JACKET생성")
    }
  }

  // 자식클래스가 부모클래스에 접근하려면 inner 키워드를 붙여줘야함
  inner class Shirt() {
    init {
      println("${this@Outer.playerName}의 셔츠입니다")
    }
  }

}

// 코틀린은 데이터에 특화된 클래스를 선언할 수 있는 문법을 제공한다. 클래스를 데이터 클래스로 선언하면, 다음과 같은 이점이 생긴다.
//
// Any 클래스에 들어있는 equals, hashCode, toString 멤버 함수가 자동으로 오버라이딩 된다.
// equals 멤버 함수는 각 프로퍼티의 값이 서로 모두 같으면 true를 반환한다.
// 객체를 복사하는 copy 함수가 자동으로 선언된다.
// 클래스를 데이터 클래스로 선언하기 위해선 다음의 규칙들을 지켜야 한다.
//
// 적어도 하나의 프로퍼티를 가져야 한다.
// 프로퍼티에 대응하지 않는 생성자 매개변수를 가질 수 없다.
// abstract, open, sealed, inner 키워드를 붙일 수 없다.
// 인터페이스만 구현할 수 있다. 코틀린 1.1 버전부터는 sealed 클래스도 상속 가능하다.
// component1, component2, ... 와 같은 이름으로 멤버 함수를 선언할 수 없다. 컴파일러가 내부적으로 사용하는 이름이기 때문이다.
data class Monster (val name: String, val atk: Int, val hp: Int)

fun main() {
  val player: Person = Person("신동철", "man")
  val queen: Person = Person("아이유", "woman")
  val king: Person = Person("강동원")

  val realPlayerName = queen.name

  println(realPlayerName)

  val superCar: Car = Car("롤스로이스", 500000000)
  val unknownCar: Car = Car("똥차")

  println(superCar.name)

  // 클래스 없이 object 표현식을 사용해 상속을 할 수 있다. 이때의 상속은 1회용이 된다.
  val customOrder: Order = object : Order("닌텐도 스위치", 1) {
    override fun print() {
      println("닌텐도가 호출되었다")
    }
  }
  customOrder.print()
  Color.colorInfoPrint()

  val clothes: Outer.Shirt = Outer("신동철").Shirt()

    val monster1: Monster = Monster("동철몬", 9999, 9999)
    val monster2: Monster = Monster("플토", 1, 1)
    val monster3: Monster = monster1.copy()

    // 데이터 클래스는 이렇게 분해가 가능하다
    val (name, atk, hp) = Monster("파이리", 1000, 1000)



}