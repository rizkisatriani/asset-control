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
WHEN LEVEL=0 THEN 'Administrator'
WHEN LEVEL=2 THEN 'Manager' 
END AS LEVEL_A,
CASE 
WHEN update_data=1 THEN 'Aktif'
WHEN update_data=0 THEN 'Tidak Aktif' 
END AS status_user,
CASE 
WHEN gender='P' THEN 'Perempuan'
WHEN gender='L' THEN 'Laki-Laki' 
END AS gender_a"); 
		$query = $this->db->get('tbl_user');
		return $query;
	}

	function insert($data)
	{ 
		$query=$this->db->insert('tbl_user', $data); 
		return $query;
	}

	function delete($data)
	{
		$this->db->insert_batch('tbl_user', $data);
	}

	function update($data,$id)
	{
		$this->db->where('id_user', $id);
		$query=$this->db->update('tbl_user', $data);
		return $query;
	}
	
}
