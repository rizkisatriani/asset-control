<?php
class Data_user_model extends CI_Model
{
	function select()
	{ 
		$query = $this->db->get('tbl_asset');
		return $query;
	}
	function select_header()
	{ 
		$query = $this->db->select("*,
CASE 
WHEN LEVEL=1 THEN 'Operator'
WHEN LEVEL=0 THEN 'Admin'
WHEN LEVEL=2 THEN 'Manager' 
END AS LEVEL_A,
CASE 
WHEN gender='P' THEN 'Perempuan'
WHEN gender='L' THEN 'Laki-Laki' 
END AS gender_a");
		$query = $this->db->get('tbl_user');
		return $query;
	}

	function insert($data)
	{
		$this->db->insert_batch('tbl_asset', $data);
	}
}
