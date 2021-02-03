import java.security.InvalidParameterException

class Matrix(private val matrix: ArrayList<ArrayList<ComplexNumber>>) {
    init {
        if(matrix.size == 0 || matrix[0].size == 0)
            throw InvalidParameterException()
        val size = matrix[0].size
        for (row in matrix){
            if(row.size != size){
                throw InvalidParameterException()
            }
        }
    }

    private fun checkSizeForMultiply(secondMatrix: Matrix){
        if (matrix[0].size != secondMatrix.matrix.size)
        {
            throw Exception("Bad dimensions")
        }
    }

    private fun checkSize(secondMatrix: Matrix){
        if (matrix.size != secondMatrix.matrix.size)
        {
            throw Exception("Bad dimensions")
        }
        if (matrix.isNotEmpty() && matrix[0].size != secondMatrix.matrix[0].size)
        {
            throw Exception("Bad dimensions")
        }
    }

    fun transpose(): Matrix{
        val newMatrix: ArrayList<ArrayList<ComplexNumber>> = arrayListOf()
        for(col in matrix[0].indices) {
            val newRow: ArrayList<ComplexNumber> = arrayListOf()
            for (row in matrix.indices) {
                newRow.add(matrix[row][col])
            }
            newMatrix.add(newRow)
        }
        return Matrix(newMatrix)
    }

    operator fun plus(secondMatrix: Matrix): Matrix{
        checkSize(secondMatrix)
        val newMatrix: ArrayList<ArrayList<ComplexNumber>> = arrayListOf()
        for(row in matrix.indices){
            val newRow: ArrayList<ComplexNumber> = arrayListOf()
            for(el in matrix[row].indices)
            {
                newRow.add(matrix[row][el] + secondMatrix.matrix[row][el])
            }
            newMatrix.add(newRow)
        }
        return Matrix(newMatrix)
    }

    operator fun minus(secondMatrix: Matrix): Matrix{
        checkSize(secondMatrix)
        val newMatrix: ArrayList<ArrayList<ComplexNumber>> = arrayListOf()
        for(row in matrix.indices){
            val newRow: ArrayList<ComplexNumber> = arrayListOf()
            for(el in matrix[row].indices)
            {
                newRow.add(matrix[row][el] - secondMatrix.matrix[row][el])
            }
            newMatrix.add(newRow)
        }
        return Matrix(newMatrix)
    }

    operator fun times(secondMatrix: Matrix): Matrix{
        checkSizeForMultiply(secondMatrix)
        val newMatrix: ArrayList<ArrayList<ComplexNumber>> = arrayListOf()
        for(newX in matrix.indices){
            val newRow: ArrayList<ComplexNumber> = arrayListOf()
            for(newY in secondMatrix.matrix[0].indices)
            {
                var sum = ComplexNumber.zero()
                for(i in matrix[newX].indices){
                    sum += matrix[newX][i] * secondMatrix.matrix[i][newY]
                }
                newRow.add(sum)
            }
            newMatrix.add(newRow)
        }
        return Matrix(newMatrix)
    }

    override fun toString(): String {
        var str = ""
        for(row in matrix){
            for(i in row){
                str += "$i "
            }
            str += "\n"
        }
        return str
    }
}