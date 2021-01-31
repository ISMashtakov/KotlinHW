class ComplexNumber(private val real: Double, private val imaginary: Double) {

    companion object {
        fun zero(): ComplexNumber{
            return ComplexNumber(0.0, 0.0)
        }
    }

    override fun toString(): String {
        var imaginaryPart = ""
        if (imaginary < 0)
            imaginaryPart = imaginary.toString() + "i"
        else if (imaginary > 0)
            imaginaryPart = "+" + imaginary.toString() + "i"
        return "$real" + imaginaryPart
    }

    operator fun plus(number: ComplexNumber): ComplexNumber{
        return ComplexNumber(real + number.real, imaginary + number.imaginary)
    }

    operator fun minus(number: ComplexNumber): ComplexNumber{
        return ComplexNumber(real - number.real, imaginary - number.imaginary)
    }

    operator fun times(number: ComplexNumber): ComplexNumber{
        return ComplexNumber(
            real * number.real - imaginary * number.imaginary,
            real * number.imaginary + imaginary * number.real
        )
    }


}