package com.ci6222.fooddelivery.network

import android.content.ContentValues
import android.graphics.Bitmap
import android.util.Log
import com.ci6222.fooddelivery.data.vos.CategoryVO
import com.ci6222.fooddelivery.data.vos.FoodItemVO
import com.ci6222.fooddelivery.data.vos.ShopVO
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.gson.Gson
import java.io.ByteArrayOutputStream
import java.util.*

object CloudFirestoreFirebaseApiImpl : FirebaseApi {

    val db = Firebase.firestore
    private val storage = FirebaseStorage.getInstance()
    val storageReference = storage.reference


    override fun getCategory(
        onSuccess: (groceries: List<CategoryVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        db.collection("Category")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFialure(it.message ?: "Please check connection")
                } ?: run{
                    val categoryList: MutableList<CategoryVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data
                        val category = CategoryVO()
                        category.name = data?.get("name") as String
                        category.id = (data["id"] as Long ).toString()
                        category.image= data["image"] as String
                        categoryList.add(category)
                    }
                    onSuccess(categoryList)
                }
            }

    }

    override fun getRestaurants(
        onSuccess: (groceries: List<ShopVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
        CloudFirestoreFirebaseApiImpl.db.collection("Restaurant")
            .addSnapshotListener { value, error ->
                error?.let {
                    onFialure(it.message ?: "Please check connection")
                } ?: run{
                    val restaurantsList: MutableList<ShopVO> = arrayListOf()

                    val result = value?.documents ?: arrayListOf()

                    for (document in result) {
                        val data = document.data
                        val restaurant = ShopVO()
                        restaurant.name = data?.get("name") as String
                        restaurant.id = (data["id"] as Long ).toString()
                        restaurant.description = data["description"] as String
                        restaurant.rating= data["rating"] as  String
                        restaurant.imgUrl = data["image"] as String?
                        restaurantsList.add(restaurant)
                    }
                    onSuccess(restaurantsList)
                }
            }
    }

    override fun getPopularFoodItemList(onSuccess: (groceries: List<FoodItemVO>) -> Unit, onFialure: (String) -> Unit) {
        CloudFirestoreFirebaseApiImpl.db.collection("foodItems")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFialure(it.message ?: "Please check connection")
                    } ?: run{
                        val foodItemList: MutableList<FoodItemVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val data = document.data
                            val foodItemVO = FoodItemVO()
                            foodItemVO.name = data?.get("name") as String
                            foodItemVO.description = data["description"] as String
                            foodItemVO.rating= data["rating"] as  String
                            foodItemVO.price = ( data["price"]  as Long )
                            foodItemVO.count = ( data["count"]  as Long )
                            foodItemVO.imgurl = data["imgUrl"] as String?
                            foodItemList.add(foodItemVO)
                        }
                        onSuccess(foodItemList)
                    }
                }
    }


    override fun getDetailFoodList(shopId: String, onSuccess: (groceries: List<FoodItemVO>, ShopVO) -> Unit, onFialure: (String) -> Unit) {
        var restaurant:ShopVO  =ShopVO()
        db.collection("Restaurant").document(shopId)
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFialure(it.message ?: "Please check connection")
                    } ?: run{

                        val data = value?.data
                        val ShopVO =ShopVO()
                       ShopVO.name = data?.get("name") as String
                       ShopVO.description = data["description"] as String?
                       ShopVO.imgUrl = data["imgurl"] as String?
                       ShopVO.rating = data["rating"] as String?

                        restaurant =ShopVO
                    }
                }

        db.collection("Restaurants/${shopId}/foodItems")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFialure(it.message ?: "Please check connection")
                    } ?: run{

                        val foodList: MutableList<FoodItemVO> = arrayListOf()

                        val result = value?.documents ?: arrayListOf()

                        for (document in result) {
                            val hashmap = document.data
                            hashmap?.put("id", document.id.toString())
                            val Data = Gson().toJson(hashmap)
                            val docsData = Gson().fromJson<FoodItemVO>(Data, FoodItemVO::class.java)
                            foodList.add(docsData)
                        }
                        onSuccess(foodList ,restaurant)
                    }
                }

    }

    override fun addCheckoutItem(
        onSuccess: (groceries: List<FoodItemVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {
//        db.collection("")
//            .document(name)
//            .set(groceryMap)
//            .addOnSuccessListener { Log.d("Success", "Successfully added grocery") }
//            .addOnFailureListener { Log.d("Failure", "Failed to add grocery") }
    }

    override fun UpadateCheckoutItem(
        onSuccess: (groceries: List<FoodItemVO>) -> Unit,
        onFialure: (String) -> Unit
    ) {

    }

    override fun addFoodItem(foodItemVO: FoodItemVO) {
        db.collection("orders")
                .document(foodItemVO?.name.toString())
                .set(foodItemVO)
                .addOnSuccessListener { Log.d("Success", "Successfully added grocery") }
                .addOnFailureListener { Log.d("Failure", "Failed to add grocery") }
    }

    override fun uploadProfileImage(image: Bitmap,onSuccess: (photoUrl : String) -> Unit, onFailure: (String) -> Unit) {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        val imageRef = storageReference.child("images/${UUID.randomUUID()}")
        val uploadTask = imageRef.putBytes(data)
        uploadTask.addOnFailureListener {
            onFailure("Update Profile Failed")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d(ContentValues.TAG, "User profile updated.")
        }


        val urlTask = uploadTask.continueWithTask {
            return@continueWithTask imageRef.downloadUrl
        }.addOnCompleteListener { task ->
            val imageUrl = task.result?.toString()
            imageUrl?.let { onSuccess(it) }
        }

    }

    override fun getCartFoodItemCount(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit) {
        db.collection("orders")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFialure(it.message ?: "Please check connection")
                    } ?: run{
                        val result = value?.documents ?: arrayListOf()
                        onSuccess(result.size.toLong())
                    }
                }
    }

    override fun getTotalPrice(onSuccess: (count: Long) -> Unit, onFialure: (String) -> Unit) {
        db.collection("orders")
                .addSnapshotListener { value, error ->
                    error?.let {
                        onFialure(it.message ?: "Please check connection")
                    } ?: run{
                        val result = value?.documents ?: arrayListOf()


                        val orderList: MutableList<FoodItemVO> = arrayListOf()

                        for (document in result) {
                            val orderData = document.data

                            orderData?.put("id", document.id.toString())
                            val Data = Gson().toJson(orderData)
                            val docsData = Gson().fromJson<FoodItemVO>(Data, FoodItemVO::class.java)
                            orderList.add(docsData)
                        }
                        var totalAmount : Long =0

                        orderList.forEach {
                            totalAmount += it.price
                        }

                        onSuccess(totalAmount)
                    }
                }
    }

}