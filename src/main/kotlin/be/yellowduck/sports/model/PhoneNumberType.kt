package be.yellowduck.sports.model

import org.hibernate.HibernateException
import org.hibernate.annotations.TypeDef
import org.hibernate.engine.spi.SharedSessionContractImplementor
import org.hibernate.type.StringType
import org.hibernate.type.descriptor.sql.VarcharTypeDescriptor
import org.hibernate.usertype.UserType
import java.io.Serializable
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException
import javax.persistence.MappedSuperclass

@MappedSuperclass
@TypeDef(defaultForType = PhoneNumber::class, typeClass = PhoneNumberType::class)
class PhoneNumberType : UserType {
    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeGet(
        rs: ResultSet,
        names: Array<String>,
        session: SharedSessionContractImplementor,
        owner: Any
    ): Any? {
        val value = StringType.INSTANCE[rs, names[0], session] as String
        return if (value == null) {
            null
        } else {
            PhoneNumber(value)
        }
    }

    @Throws(HibernateException::class, SQLException::class)
    override fun nullSafeSet(st: PreparedStatement, value: Any, index: Int, session: SharedSessionContractImplementor) {
        if (value == null) StringType.INSTANCE[st, null, index] =
            session else StringType.INSTANCE[st, value.toString(), index] = session
    }

    override fun sqlTypes(): IntArray {
        return intArrayOf(VarcharTypeDescriptor.INSTANCE.sqlType)
    }

    override fun returnedClass(): Class<PhoneNumber> {
        return PhoneNumber::class.java
    }

    @Throws(HibernateException::class)
    override fun equals(x: Any, y: Any): Boolean {
        return x == y
    }

    @Throws(HibernateException::class)
    override fun hashCode(x: Any): Int {
        return x.hashCode()
    }

    @Throws(HibernateException::class)
    override fun deepCopy(value: Any): Any {
        return value
    }

    override fun isMutable(): Boolean {
        return false
    }

    @Throws(HibernateException::class)
    override fun disassemble(value: Any): Serializable {
        return value as Serializable
    }

    @Throws(HibernateException::class)
    override fun assemble(cached: Serializable, owner: Any): Any {
        return cached
    }

    @Throws(HibernateException::class)
    override fun replace(original: Any, target: Any, owner: Any): Any {
        return original
    }
}