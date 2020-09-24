<?php
defined('BASEPATH') OR exit('No direct script access allowed');

class Api_stock extends CI_Controller {

	/**
	 * Index Page for this controller.
	 *
	 * Maps to the following URL
	 * 		http://example.com/index.php/welcome
	 *	- or -
	 * 		http://example.com/index.php/welcome/index
	 *	- or -
	 * Since this controller is set as the default controller in
	 * config/routes.php, it's displayed at http://example.com/
	 *
	 * So any other public methods not prefixed with an underscore will
	 * map to /index.php/welcome/<method_name>
	 * @see https://codeigniter.com/user_guide/general/urls.html
	 */
 
	public function simpan()
	{  
	$json=$this->input->post('data');
	$data=json_decode($json); 
	for ($i=0; $i <count($data) ; $i++) { 
		$data_bar = array('barcode'=>$data[$i]->barcode);
		$chek_data=$this->db->query("select * from tbl_stok where barcode=".$data[$i]->barcode)->num_rows(); 
		switch ($chek_data) {
			case 0:
			$result = $this->db->insert("tbl_stok",$data_bar);	
				break;
			
			default:
			$result ='0';
				break;
		}
		echo $result;
	}
	}

	public function get_data()
	{   
				$chek_data=$this->db->query("SELECT 
		  fixed_asset_goup,
		  fixed_asset_number,
		  reference_asset_number,
		  NAME,
		  name2,
		  description,
		  STATUS,
		  TYPE,
		  location 
		FROM db_stok.tbl_asset
		ORDER BY fixed_asset_number")->result(); 
			$data['data'] = $chek_data; 
			$data['tanggal'] = date("Y/m/d");
				echo json_encode($data);
	}
}
