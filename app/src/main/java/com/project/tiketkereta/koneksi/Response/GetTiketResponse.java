package com.project.tiketkereta.koneksi.Response;

import java.util.List;
import com.google.gson.annotations.SerializedName;
import com.project.tiketkereta.koneksi.Model.DataPemesanan;
import com.project.tiketkereta.koneksi.Model.DataTiket;

public class GetTiketResponse{

	@SerializedName("data")
	private List<DataTiket> data;

	@SerializedName("status")
	private String status;

	public void setData(List<DataTiket> data){
		this.data = data;
	}

	public List<DataTiket> getData(){
		return data;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}
}