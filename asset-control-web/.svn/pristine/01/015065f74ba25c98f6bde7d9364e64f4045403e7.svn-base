<?php if ( ! defined('BASEPATH')) exit('No direct script access allowed');

class Excel_import extends CI_Controller
{
	public function __construct()
	{
		parent::__construct();
		$this->load->model('excel_import_model');
		$this->load->library('excel');
	}

	function index()
	{
		$this->load->view('excel_import');
	}
	
	function fetch()
	{
		$data = $this->excel_import_model->select();  
		$x['data']= $data->result() ; 
		$this->load->view('upload_data/load_data',$x);
	}

	function import()
	{
	
		$data_header[] = array(
						'tanggal'					=>	$this->input->post('tanggal'),
						'lokasi'			=>	$this->input->post('lokasi') ,
						'id_user'		=>$this->session->userdata('logged_in')
					);
		$header=$this->db->insert_batch('tbl_stock_take', $data_header);
		$id_stock_take=$this->db->insert_id();
		if($header){
		if(isset($_FILES["file"]["name"]))
		{
			$path = $_FILES["file"]["tmp_name"];
			$object = PHPExcel_IOFactory::load($path);
			foreach($object->getWorksheetIterator() as $worksheet)
			{
				$highestRow = $worksheet->getHighestRow();
				$highestColumn = $worksheet->getHighestColumn();
				for($row=2; $row<=$highestRow; $row++)
				{
					$id_asset = $worksheet->getCellByColumnAndRow(0, $row)->getValue();
					$fixed_asset_goup = $worksheet->getCellByColumnAndRow(1, $row)->getValue();
					$fixed_asset_number = $worksheet->getCellByColumnAndRow(2, $row)->getValue();
					$reference_asset_number = $worksheet->getCellByColumnAndRow(3, $row)->getValue();
					$name = $worksheet->getCellByColumnAndRow(4, $row)->getValue();
					$name2 = $worksheet->getCellByColumnAndRow(5, $row)->getValue();
					$description = $worksheet->getCellByColumnAndRow(6, $row)->getValue();
					$status = $worksheet->getCellByColumnAndRow(7, $row)->getValue();
					$type = $worksheet->getCellByColumnAndRow(8, $row)->getValue();
					$location = $worksheet->getCellByColumnAndRow(9, $row)->getValue();
					$responsible = $worksheet->getCellByColumnAndRow(10, $row)->getValue();
					$acquisiton = $worksheet->getCellByColumnAndRow(11, $row)->getValue();
					$depreciation = $worksheet->getCellByColumnAndRow(12, $row)->getValue();
					$net_book_price = $worksheet->getCellByColumnAndRow(13, $row)->getValue();
					$acquisition_date = $worksheet->getCellByColumnAndRow(14, $row)->getValue();
					$service_life = $worksheet->getCellByColumnAndRow(15, $row)->getValue();
					$depreciation_remaining = $worksheet->getCellByColumnAndRow(16, $row)->getValue();
					$curent = $worksheet->getCellByColumnAndRow(17, $row)->getValue();
					$operations = $worksheet->getCellByColumnAndRow(18, $row)->getValue();
					$tax = $worksheet->getCellByColumnAndRow(19, $row)->getValue();
					$data[] = array(
						'fixed_asset_goup'					=>	$id_asset,
						'fixed_asset_number'			=>	$fixed_asset_goup,
						'id_stock_take'		=>	$id_stock_take,
						'reference_asset_number'		=>	$fixed_asset_number,
						'name'	=>	$reference_asset_number,
						'name2'						=>	$name,
						'description'						=>	$name2,
						'status'				=>	$description,
						'type'					=>	$status,
						'location'						=>	$type,
						'responsible'					=>	$location,
						'acquisiton'				=>	$responsible,
						'depreciation'				=>	$acquisiton,
						'net_book_price'				=>	$depreciation,
						'acquisition_date'			=>	$net_book_price,
						'service_life'			=>	$acquisition_date,
						'depreciation_remaining'				=>	$service_life,
						'curent'	=>	$depreciation_remaining,
						'operations'					=>	$curent,
						'tax'				=>	$operations
					);
				}
			}
			$cek_insert=$this->excel_import_model->insert($data);
			if($cek_insert){
				$result[]= array('status'					=>	true,
						'message'			=>	'Data berhasil tersimpan',
						'id_stock_take'		=>	$id_stock_take);
			 echo json_encode($result);
			}else{
				$result[]= array('status'					=>	false,
						'message'			=>	'Data gagal tersimpan',
						'id_stock_take'		=>	'undefined');
			 echo json_encode($result);
			}
			
		}	else{
			$result[]= array('status'					=>	false,
						'message'			=>	'File gagal terupload',
						'id_stock_take'		=>	'undefined');
			 echo json_encode($result);
		}
	}

}//if header
}

?>