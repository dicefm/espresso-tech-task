package fm.dice.data.common.mapper

abstract class Mapper<in From, To> {

    abstract fun mapFrom(from: From): To
}