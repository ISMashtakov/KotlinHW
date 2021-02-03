fun main() {
    val matrix1 = Matrix(
        arrayListOf(
            arrayListOf(ComplexNumber(1.0, 0.0), ComplexNumber(2.0, 0.0), ComplexNumber(1.0, 0.0)),
            arrayListOf(ComplexNumber(0.0, 0.0), ComplexNumber(1.0, 0.0), ComplexNumber(2.0, 0.0)),
        )
    )

    val matrix2 = Matrix(
        arrayListOf(
            arrayListOf(ComplexNumber(1.0, 0.0), ComplexNumber(0.0, 0.0)),
            arrayListOf(ComplexNumber(0.0, 0.0), ComplexNumber(1.0, 0.0)),
            arrayListOf(ComplexNumber(1.0, 0.0), ComplexNumber(1.0, 0.0))
        )
    )
    print((matrix1 * matrix2).toString())
    print(matrix1.transpose().toString())
}