package com.project.tiketkereta.koneksi.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.project.tiketkereta.koneksi.Model.DataPemesanan;

public class GetPemesananResponse{

	@SerializedName("data")
	private List<DataPemesanan> data;

	@SerializedName("status")
	private String status;

	public void setData(List<DataPemesanan> data){
		this.data = data;
	}

	public List<DataPemesanan> getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}