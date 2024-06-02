package com.example.skills.data.entity

data class Service(
    val id: Int,
    val name: String,
    val description: String,
    val price: Long,
    val duration: Duration,
    val category: Category
)

data class ServiceRequest(
    val name: String,
    val description: String,
    val price: Int,
    val duration: Duration,
    val category: Category
)

data class EditServiceRequest(
    val name: String,
    val description: String,
    val price: Int,
    val duration: Duration
)

data class Duration(var hours: Int, var minutes: Int){
    override fun toString(): String {
        return (hours * 60 + minutes).toString()
    }
}