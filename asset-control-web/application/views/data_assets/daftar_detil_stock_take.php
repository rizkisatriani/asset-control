
 <div class="row clearfix">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                           
                            <ul class="header-dropdown dropdown">
                                
                                <li><a href="javascript:void(0);" class="full-screen"><i class="icon-frame"></i></a></li>
                                <li class="dropdown">
                                    <a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="javascript:vivifyoid(0);">Action</a></li>
                                        <li><a href="javascript:void(0);">Another Action</a></li>
                                        <li><a href="javascript:void(0);">Something else</a></li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                        <div class="body">
                             <div>
                                    <h4 class="card-title text-left">Data Asset</h4> 
                                     <p>Daftar data asset yang akan di stock take </p>
                            </div>
                            <hr>
                            <div class="table-responsive">
                               <table id="example" class="table table-hover js-basic-example dataTable table-custom spacing5"> 
                                  <thead>
                                            <tr>
                                                <th class="mb-0">Status Scan</th>
                                                <th class="mb-0">Operator Scan</th>
                                                <th class="mb-0">Fixed Asset Group</th>
                                                <th class="w200">Fixed Asset Number</th>
                                                <th class="w100">Reference Asset Number</th>
                                                <th class="w100">Name</th>
                                                <th class="w100">Name</th>
                                                <th class="w100">Deskripsi</th> 
                                                <th class="w100">Lokasi</th>
                                            </tr>
                                  </thead> 
                                    <tbody>
                                            <?php foreach ($data as $key ) { ?>
                                            
                                              <tr>
                                                <td><?php if($key->status_scan==0){ 
                                                    echo "<button   class='btn btn-outline-danger ' style='margin:5px;'>".$key->ket_scan."</button>";
                                                }else{
                                                    echo "<button   class='btn btn-outline-success ' style='margin:5px;'>".$key->ket_scan."</button>";
                                                }?></td>
                                                <td><?php echo $key->nama_lengkap;?></td>
                                                <td><?php echo $key->fixed_asset_goup;?></td>
                                                <td><?php echo $key->fixed_asset_number;?></td>
                                                <td><?php echo $key->reference_asset_number;?></td>
                                                <td><?php echo $key->name;?></td>
                                                <td><?php echo $key->name2;?></td>
                                                <td><?php echo $key->description;?></td> 
                                                <td><?php echo "";?></td> 
                                            </tr>

                                            <?php } ?>
                                    </tbody>
                                 
                                </table> 
                            </div>
                        </div>
                    </div>
                </div>  
            </div>

 
<script>$(document).ready(function() {
    //$('#example').DataTable();
    $('#example').DataTable( {
    fixedHeader: true
} );
} );
</script>