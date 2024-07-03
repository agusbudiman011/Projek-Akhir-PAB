package com.project.tiketkereta.koneksi.Response;

import com.google.gson.annotations.SerializedName;
import com.project.tiketkereta.koneksi.Model.DataUser;

public class GetUserResponse{

	@SerializedName("data")
	private DataUser data;

	@SerializedName("status")
	private String status;

	public void setData(DataUser data){
		this.data = data;
	}

	public DataUser getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}