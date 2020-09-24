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
 
 	public function login(){ 
	$nip=$this->input->post('nip');
	$password=$this->input->post('password');
	$this->db->select('*');
			$this->db->where('nip', $nip);  
			$query = $this->db->get('tbl_user', 1);
			$data=$query->row();
			if($query->num_rows()==1&&$password=$data->password){
			$pesan="success";
			$kode=200;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['data']= $query ; 
			$x['id_user']= $data->id_user ; 
			$x['username']= $data->username ; 
			}else{ 
				$pesan="fail";
				$kode=404;
			$x['message']=$pesan;
			$x['kode']=$kode;
			$x['data']= 'null' ; 
			}
		echo json_encode($x);
	}

	public function simpan()
	{  
	$json=$this->input->post('data');
 
	$data=json_decode($json);
 
	$query=$this->db->insert_batch('tbl_asset_result', $data->data);
	if($query){
		$pesan="success";
		$kode=200;
		$x['message']=$pesan;
		$x['kode']=$kode;
		$x['data']= $query ; 
		}else{ 
			$pesan="fail";
			$kode=404;
		$x['message']=$pesan;
		$x['kode']=$kode;
		$x['data']= 'null' ; 
		}
		echo json_encode($x);
	}
 


	public function get_data_header()
	{   
		$id=$this->input->post('id');
				$chek_data=$this->db->query("SELECT
    tbl_stock_take.tanggal
    , tbl_stock_take.lokasi
    , tbl_stock_take.id_stock_take
    , tbl_stock_take.tangal_input
    , user_stock_take.id_user
    , tbl_user.username
    , tbl_user.nama_lengkap
FROM
    db_stok.tbl_stock_take
    INNER JOIN db_stok.user_stock_take 
        ON (tbl_stock_take.id_stock_take = user_stock_take.id_stock_take)
    INNER JOIN db_stok.tbl_user 
        ON (tbl_user.id_user = user_stock_take.id_user)
        WHERE user_stock_take.id_user=".$id." AND tanggal >= CURDATE();")->result(); 
			$data['data'] = $chek_data; 
			$data['tanggal'] = date("Y/m/d");
				echo json_encode($data);
	}
	

	public function get_data()
	{   
		$id=$this->input->post('id');
				$chek_data=$this->db->query("SELECT
  id_asset,
  id_stock_take,
  fixed_asset_goup,
  fixed_asset_number,
  reference_asset_number,
  NAME,
  name2,
  description,
  STATUS,
  TYPE,
  location,
  responsible,
  acquisiton,
  depreciation,
  net_book_price,
  acquisition_date,
  service_life,
  depreciation_remaining,
  curent,
  operations,
  tax
FROM db_stok.tbl_asset
WHERE id_stock_take=".$id." ")->result(); 
			$data['data'] = $chek_data; 
			$data['tanggal'] = date("Y/m/d");
				echo json_encode($data);
	}
}
