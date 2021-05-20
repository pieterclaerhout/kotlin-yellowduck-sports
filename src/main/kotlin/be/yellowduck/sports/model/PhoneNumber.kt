package be.yellowduck.sports.model

class PhoneNumber(var value: String) {

    override fun toString(): String {
        return value
    }

    override fun equals(`object`: Any?): Boolean {
        if (`object` !is PhoneNumber) {
            return false
        }
        val that = `object`
        return value != null && that.value != null && value == that.value
    }

    override fun hashCode(): Int {
        return value.hashCode()
    }

}
