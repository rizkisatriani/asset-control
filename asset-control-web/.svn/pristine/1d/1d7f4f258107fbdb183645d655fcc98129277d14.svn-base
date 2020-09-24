<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Data_assets extends CI_Controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->model('excel_import_model');
		$this->load->model('data_asset_model');
		$this->load->library('excel');
	}
 
	function fetch()
	{
		$data = $this->excel_import_model->select(); 
		$x['data']= $data->result() ; 
		$this->load->view('upload_data/load_data',$x);
	}

	function fetch_header()
	{
		$data = $this->data_asset_model->select_header(); 
		$x['data']= $data->result() ; 
		$this->load->view('data_assets/daftar_stock_take',$x);
	}
	 
}

?>