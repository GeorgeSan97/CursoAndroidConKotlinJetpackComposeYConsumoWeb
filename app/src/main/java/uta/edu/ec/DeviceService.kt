package uta.edu.ec

import retrofit2.http.GET

interface DeviceService {
    @GET(Constant.OBJECTS_PATH)
    suspend fun getAllDevices(): List<Device>
}