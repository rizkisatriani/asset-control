<?php
class Excel_import_model extends CI_Model
{
	function select()
	{ 
		$id=$this->input->post('id');
		 $this->db->where('id_stock_take',$id);
		$query = $this->db->get('tbl_asset'); 
		return $query;
	}

	function insert($data)
	{
		$result=$this->db->insert_batch('tbl_asset', $data);
		return $result;
	}
}
