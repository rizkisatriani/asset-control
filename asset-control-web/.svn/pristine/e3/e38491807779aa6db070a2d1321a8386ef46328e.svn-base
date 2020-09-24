<?php
class Data_asset_model extends CI_Model
{
	function select($id)
	{  
		$this->db->where('id_stock_take',$id);
		$query = $this->db->get('tbl_asset');
		return $query;
	}
	
	function select_header()
	{ 
		$query = $this->db->select('tbl_stock_take.id_stock_take
    , DATE_FORMAT(tanggal, "%d/%m/%Y") AS tanggal
    , tbl_stock_take.lokasi 
    , tbl_stock_take.id_user
    , GROUP_CONCAT(tbl_user.nama_lengkap SEPARATOR ",") AS USER');
		$this->db->from('tbl_stock_take');
		$this->db->join('user_stock_take', 'tbl_stock_take.id_stock_take = user_stock_take.id_stock_take', 'left');
		$this->db->join('tbl_user', 'tbl_user.id_user = user_stock_take.id_user', 'left');
		$this->db->group_by("id_stock_take"); 
		$query = $this->db->get();
		return $query;
	}

	function select_result($id_stock_take)
	{ 
		$query = $this->db->select("CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN 'Sudah Upload' ELSE 'Belum Upload' END AS ket_scan,
			CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN 1 ELSE 0 END AS status_scan,
		    tbl_stock_take.id_stock_take
		    , tbl_stock_take.tanggal
		    , tbl_stock_take.lokasi
		    , tbl_stock_take.tangal_input
		    , tbl_stock_take.id_user 
		    ,CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN tbl_asset_result.id_asset ELSE tbl_asset.id_asset END AS id_asset  
		    , tbl_asset.fixed_asset_goup
		    , tbl_asset.fixed_asset_number
		    , tbl_asset.reference_asset_number
		    , tbl_asset.name 
		    , tbl_asset.description
		    ,CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN tbl_asset_result.status ELSE tbl_asset.status END AS STATUS  
		    ,CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN tbl_asset_result.type ELSE tbl_asset.type END AS TYPE  
		    ,CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN tbl_asset_result.location ELSE tbl_asset.location END AS location  
		    ,CASE WHEN tbl_asset.id_asset=tbl_asset_result.id_asset THEN tbl_asset_result.name2 ELSE tbl_asset.name2 END AS name2  
		    , tbl_asset.responsible
		    , tbl_asset_result.id_user
		    , tbl_user.username
		    , tbl_user.nama_lengkap", FALSE);
		$this->db->from('tbl_asset');
		$this->db->join('tbl_asset_result', 'tbl_asset_result.id_asset = tbl_asset.id_asset', 'left');
		$this->db->join('tbl_stock_take', 'tbl_asset.id_stock_take = tbl_stock_take.id_stock_take', 'left');
		$this->db->join('tbl_user', 'tbl_user.id_user = tbl_asset_result.id_user', 'left');  
		$this->db->where('tbl_asset.id_stock_take',$id_stock_take);
		$query = $this->db->get();
		return $query;
	}

	function insert($data)
	{
		$this->db->insert_batch('tbl_asset', $data);
	}
}
