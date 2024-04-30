package com.example.skills.data

open class User (
    open val id: Long,
    open var email: String,
    open var firstName: String,
    open var lastName: String,
    open var phone: String,
    open var imageId: String,
    open var password: String,
    open var role: Role
)