package useGrammer

// Array관련 문법들 정리

fun main () {
  val nameArray: Array<String> = arrayOf("찬웅","동철","하린","상협","원석")

  println(joinToArray(nameArray))
  println(mapToArray(nameArray))
  println(dropToArray(nameArray))
  println(sortToArray(nameArray))

}


// *의 정체는 스타프로젝션 타입 파라미터가 2개 이상일 때 사용함
// 인자를 알 수 없는 제네릭 타입을 표현할 때 사용한다는 의미로 알고 있으면 됨
// 지금 상황에선 String이라는 타입자체를 정확히 알고 있기 때문에 제네릭을 쓰는게 맞긴 하지만
// 신기해서 걍 이걸로 써봄
fun joinToArray (inputArray: Array<*>): String {

  // joinToString은 배열을 separator값으로 구분지어 String값으로 합쳐줌
  return inputArray.joinToString(",")
}

fun<T> mapToArray (inputArray: Array<T>): String {
  // map collection을 사용하면 자동으로 List형식으로 형변환됨
  val cuteNamingArray: List<String> = inputArray.map { "${it}이" }

  return cuteNamingArray.joinToString(",")
}

fun<T> dropToArray (inputArray: Array<T>): String {
  // drop collection을 사용하면 자동으로 List형식으로 형변환됨 거의 array쓰지 말라는건가...
  // drop은 해당 순서만 지우는게 아니라 그전꺼까지 다 지워버림
  val dropLastIndexArray: List<T> = inputArray.dropLast(1)

  return dropLastIndexArray.joinToString { "${it}" }
}

fun<T> sortToArray (inputArray: Array<T>): String {
  // 얘는 반환값없이 그냥 바로 내부에서 sorting시켜주나봄
  inputArray.sort()

  // index 0번부터 3번까지만 sorting해줌
  // inputArray.sort(0,4)

  return inputArray.joinToString { "${it}" }
}