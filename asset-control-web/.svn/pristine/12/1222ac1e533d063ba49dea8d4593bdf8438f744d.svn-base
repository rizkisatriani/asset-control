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
	 
}

?>