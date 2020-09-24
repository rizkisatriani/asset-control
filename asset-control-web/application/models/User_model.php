<?php
class User_model extends CI_Model
{
	function __construct()
	{
		parent :: __construct();
	}
 
 	function check_login( $nip )
		{
			// $this->support = $this->load->database('db_sigma', true);

			$this->db->select('*');
			$this->db->where('nip', $nip); 

			$query = $this->db->get('tbl_user', 1);

			if ( $query->num_rows() == 1 ) 
			{
				return $query->row();
			}
			else
			{
				return false;
			}
		}
}
