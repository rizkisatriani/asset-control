
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
                                    <h4 class="card-title text-left">Data User</h4> 
                                     <p>Daftar data user yang dapat menggunakan system</p>
                            </div>
                            <hr>
                            <div class="table-responsive">
                               <table id="example" class="table table-hover js-basic-example dataTable table-custom spacing5"> 
                                  <thead>
                                            <tr>
                                                <th class="w200">NIP</th>
                                                <th class="mb-0">Nama</th> 
                                                <th class="mb-0">Username</th> 
                                                <th class="mb-0">Level</th> 
                                                <th class="mb-0">Jenis Kelamin</th> 
                                                <th class="mb-0">Status Update</th> 
                                            </tr>
                                  </thead> 
                                    <tbody>
                                            <?php foreach ($data as $key ) { ?>
                                            
                                              <tr>
                                                <th><?php echo $key->nip;?></th>
                                                <th><?php echo $key->nama_lengkap;?></th> 
                                                <th><?php echo $key->username;?></th> 
                                                <th><?php echo $key->LEVEL_A;?></th> 
                                                <th><?php echo $key->gender_a;?></th> 
                                                <th><?php echo $key->update_data;?></th> 
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