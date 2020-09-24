<?php
class Data_asset_model extends CI_Model
{
	function select()
	{ 
		$query = $this->db->get('tbl_asset');
		return $query;
	}
	function select_header()
	{ 
		$query = $this->db->select('DATE_FORMAT(tanggal, "%d / %M / %Y") as tanggal, lokasi');
		$query = $this->db->get('tbl_stock_take');
		return $query;
	}

	function insert($data)
	{
		$this->db->insert_batch('tbl_asset', $data);
	}
}
