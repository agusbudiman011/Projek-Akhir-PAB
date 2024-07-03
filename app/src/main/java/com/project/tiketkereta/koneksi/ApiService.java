package com.project.tiketkereta.koneksi;

import com.project.tiketkereta.koneksi.Response.GetPemesananResponse;
import com.project.tiketkereta.koneksi.Response.GetTiketResponse;
import com.project.tiketkereta.koneksi.Response.GetUserResponse;
import com.project.tiketkereta.koneksi.Response.LoginResponse;
import com.project.tiketkereta.koneksi.Response.PesanTiketResponse;
import com.project.tiketkereta.koneksi.Response.RegisterResponse;
import com.project.tiketkereta.koneksi.Response.UpdateUserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {

    @GET("GetData.php")
    Call<GetTiketResponse> getDataTiket();

    @FormUrlEncoded
    @POST("PesanTiket.php")
    Call<PesanTiketResponse> pesanTiket(
            @Field("id_tiket") String idBarang,
            @Field("nama_penerima") String namaPenerima,
            @Field("jumlah_pesanan") int jumlahPesanan,
            @Field("nomor_hp") String nomorHp,
            @Field("id_user") int id_user
    );

    @FormUrlEncoded
    @POST("Register.php")
    Call<RegisterResponse> registerUser(
            @Field("username") String username,
            @Field("email") String email,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("Login.php")
    Call<LoginResponse> loginUser(
            @Field("username") String username,
            @Field("password") String password
    );

    @FormUrlEncoded
    @POST("GetPemesanan.php")
    Call<GetPemesananResponse> getPesananTiket(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("UpdateUser.php")
    Call<UpdateUserResponse> updateUser(
            @Field("id") int id,
            @Field("username") String username,
            @Field("password") String password,
            @Field("email") String email
    );

    @FormUrlEncoded
    @POST("GetUser.php")
    Call<GetUserResponse> getUser(
            @Field("id") int id
    );

}



