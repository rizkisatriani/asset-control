<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Data_user extends CI_Controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->model('excel_import_model');
		$this->load->model('data_user_model');
		$this->load->library('excel');
	}
  
	function fetch_header()
	{
		$data = $this->data_user_model->select_header(); 
		$x['data']= $data->result() ; 
		$this->load->view('data_user/daftar_user',$x);
	}

		function fetch()
	{ 
		$data = $this->data_user_model->select_header(); 
		if($data){
			$pesan="success";
			$kode=200;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['data']= json_encode($data->result()); 
		}else{ 
			$pesan="fail";
			$kode=404;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['data']= '' ; 
		} 
		echo json_encode($x);
	}

		function tambah()
	{ 
		$this->load->view('data_user/tambah_user');
	}

	function insert(){
		$data = array(
						'username'		=>	$this->input->post('username'),
						'nama_lengkap'	=>	$this->input->post('nama_lengkap') ,
						'level'			=>	$this->input->post('level'), 
						'nip'			=>	$this->input->post('nip'),
						'gender'		=>	$this->input->post('gender')
					);
		$cek_insert=$this->data_user_model->insert($data);
		if($cek_insert){

			$pesan="success";
			$kode=200;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['status']= true; 
		}else{
			$pesan="fail";
			$kode=404;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['status']= false; 
		}
		echo json_encode($x);
	}

	function update(){
		$data = array(
						'username'		=>	$this->input->post('username'),
						'nama_lengkap'	=>	$this->input->post('nama_lengkap') ,
						'level'			=>	$this->input->post('level'), 
						'nip'			=>	$this->input->post('nip'),
						'gender'		=>	$this->input->post('gender')
					);
		$id=$this->input->post('id');
		$cek_insert=$this->data_user_model->update($data,$id);
		if($cek_insert){ 
			$pesan="success";
			$kode=200;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['status']= true; 
		}else{
			$pesan="fail";
			$kode=404;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['status']= false; 
		}
		echo json_encode($x);
	}
	 
}

?> 