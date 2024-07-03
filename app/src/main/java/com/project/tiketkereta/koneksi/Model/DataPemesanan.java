package com.project.tiketkereta.koneksi.Model;

import com.google.gson.annotations.SerializedName;

public class DataPemesanan {

	@SerializedName("id_pemesanan")
	private int idPemesanan;

	@SerializedName("tanggal_pemesanan")
	private String tanggalPemesanan;

	@SerializedName("harga")
	private String harga;

	@SerializedName("no_hp")
	private String noHp;

	@SerializedName("kelas")
	private String kelas;

	@SerializedName("pemberangkatan")
	private String pemberangkatan;

	@SerializedName("jurusan")
	private String jurusan;

	@SerializedName("armada")
	private String armada;

	@SerializedName("total_bayar")
	private int totalBayar;

	public void setIdPemesanan(int idPemesanan){
		this.idPemesanan = idPemesanan;
	}

	public int getIdPemesanan(){
		return idPemesanan;
	}

	public void setTanggalPemesanan(String tanggalPemesanan){
		this.tanggalPemesanan = tanggalPemesanan;
	}

	public String getTanggalPemesanan(){
		return tanggalPemesanan;
	}

	public void setHarga(String harga){
		this.harga = harga;
	}

	public String getHarga(){
		return harga;
	}

	public void setNoHp(String noHp){
		this.noHp = noHp;
	}

	public String getNoHp(){
		return noHp;
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

	public void setArmada(String armada){
		this.armada = armada;
	}

	public String getArmada(){
		return armada;
	}

	public void setTotalBayar(int totalBayar){
		this.totalBayar = totalBayar;
	}

	public int getTotalBayar(){
		return totalBayar;
	}
}