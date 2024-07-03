package com.project.tiketkereta.koneksi.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class DataTiket implements Parcelable {
	@SerializedName("keterangan")
	private String keterangan;

	@SerializedName("harga")
	private String harga;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("pemberangkatan")
	private String pemberangkatan;

	@SerializedName("jurusan")
	private String jurusan;

	@SerializedName("id_tiket")
	private String idTiket;

	@SerializedName("armada")
	private String armada;

	protected DataTiket(Parcel in) {
		keterangan = in.readString();
		harga = in.readString();
		kelas = in.readString();
		pemberangkatan = in.readString();
		jurusan = in.readString();
		idTiket = in.readString();
		armada = in.readString();
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(keterangan);
		dest.writeString(harga);
		dest.writeString(kelas);
		dest.writeString(pemberangkatan);
		dest.writeString(jurusan);
		dest.writeString(idTiket);
		dest.writeString(armada);
	}

	@Override
	public int describeContents() {
		return 0;
	}

	public static final Creator<DataTiket> CREATOR = new Creator<DataTiket>() {
		@Override
		public DataTiket createFromParcel(Parcel in) {
			return new DataTiket(in);
		}

		@Override
		public DataTiket[] newArray(int size) {
			return new DataTiket[size];
		}
	};

	public void setKeterangan(String keterangan){
		this.keterangan = keterangan;
	}

	public String getKeterangan(){
		return keterangan;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setKelas(String kelas){
		this.kelas = kelas;
	}

	public String getKelas(){
		return kelas;
	}

	public void setPemberangkatan(String pemberangkatan){
		this.pemberangkatan = pemberangkatan;
	}

	public String getPemberangkatan(){
		return pemberangkatan;
	}

	public void setJurusan(String jurusan){
		this.jurusan = jurusan;
	}

	public String getJurusan(){
		return jurusan;
	}

	public void setIdTiket(String idTiket){
		this.idTiket = idTiket;
	}

	public String getIdTiket(){
		return idTiket;
	}

	public void setArmada(String armada){
		this.armada = armada;
	}

	public String getArmada(){
		return armada;
	}
}
