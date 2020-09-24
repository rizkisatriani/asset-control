
 <div class="row clearfix">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="header">
                <!--            <a class='btn btn-primary float-right' onclick="tambah()" data-toggle="modal" href="#" data-target="#addevent" data-backdrop="static" data-keyboard="false"><i class="fa fa-plus"></i><span> Tambah</span></a> -->

                           <a class='btn btn-primary float-right' onclick="tambah()"  href="#" ><i class="fa fa-plus"></i><span> Tambah</span></a>
                           <br>
                           <br>                       
                        </div>
                        <div class="body">
                             <div>
                                    <h4 class="card-title text-left">Data User</h4> 
                                     <p>Daftar data user yang dapat menggunakan system</p>
                            </div>
                            <hr>
                            <div class="table-responsive">
                               <table id="table" class="table table-hover js-basic-example dataTable table-custom spacing5"> 
                                    <thead>
                                            <tr>
                                                <th style="display:none;">id</th>
                                                <th class="w200">NIP</th>
                                                <th class="mb-0">Nama</th> 
                                                <th class="mb-0">Username</th> 
                                                <th class="mb-0">Level</th> 
                                                <th class="mb-0">Jenis Kelamin</th> 
                                                <th class="mb-0">Status </th> 
                                                <th class="mb-0">Action</th> 
                                            </tr>
                                    </thead>
                                    <tbody>
                                            <?php foreach ($data as $key ) { ?>
                                            
                                              <tr>
                                                <td style="display:none;"><?php echo $key->id_user;?></td>
                                                <td><?php echo $key->nip;?></td>
                                                <td><?php echo $key->nama_lengkap;?></td> 
                                                <td><?php echo $key->username;?></td> 
                                                <td><?php echo $key->LEVEL_A;?></td> 
                                                <td><?php echo $key->gender_a;?></td> 
                                                <td><?php echo $key->status_user;?></td> 
                                                 <td><div class='btn btn-outline-success edit'   ><i class="fa fa-pencil"></i><span> Edit</span></div> 

                                                  <?php if($key->update_data==0){
                                                    echo "<a class='btn btn-outline-success col-lg-8' href=\"<?php echo site_url()?>/home\"><i class=\"fa fa-toggle-off\"></i><span> Aktifkan</span></a>";
                                                   }else{
                                                    echo "<a class='btn btn-outline-success col-lg-8' href=\"<?php echo site_url()?>/home\"><i class=\"fa fa-toggle-on\"></i><span> Non Aktifkan</span></a>";
                                                 } ;?>
                                                    
                                                </td> 
                                            </tr> 
                                            <?php } ?> 
                                    </tbody>        
                                </table> 
                            </div>
                        </div>
                    </div>
                </div>  
            </div>

 
<div class="modal fade" id="EditModal" tabindex="-1" role="dialog">
    <div class="modal-dialog modal-lg" role="document">
        <div class="modal-content ">
            <div class="modal-header">
              <a class="align-middle"><i class="fa fa-address-card-o fa-2x text-success" aria-hidden="true"></i><span > Edit Data User</span>  </a>
            <hr>
            </div>
            <input type="hidden" class="form-control" id="id"  placeholder="Tulis Nama User">
            <div class="modal-body col-sm-12 col-md-12 col-lg-12 mx-auto">
                <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">Nama Lengkap</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="nama"  placeholder="Tulis Nama User">
                </div>

              </div>  

               <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">NIP</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="nip" placeholder="Tulis NIP User">
                </div>
              </div> 
              
               <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">Username</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="username" placeholder="Tulis NIP User">
                </div>
              </div> 

                <div class="form-group row">
                      <label for="staticEmail" class="col-sm-3 col-form-label">Level User</label>
                <div class="col-sm-9">
                  <select id="level" class="form-control form-control-sm">
                    <option>Pilih</option>
                    <option>Administrator</option>
                    <option>Operator</option> 
                  </select>
                </div>
              </div>

              <div class="form-group row">
                      <label for="staticEmail" class="col-sm-3 col-form-label">Jenis Kelamin</label>
                <div class="col-sm-9">
              <div class="form-check">
                  <input class="form-check-input" type="radio" name="gender" id="laki" value="L" checked>
                  <label class="form-check-label" for="exampleRadios1">
                    Laki - Laki
                  </label>
                </div>
                <div class="form-check">
                  <input class="form-check-input" type="radio" name="gender" id="perempuan" value="P">
                  <label class="form-check-label" for="exampleRadios2">
                    Perempuan
                  </label>
                </div>
                </div>
              </div> 

            </div>



            <div class="modal-footer col-sm-12">
                <button type="button" id="upload" class="btn   btn-outline-primary col-sm-3" onclick="update()"  >Simpan</button>
                <button type="button" class="btn  btn-outline-danger col-sm-3" data-dismiss="modal">Batal</button>
            </div>
        </div>
    </div>
</div>
<script>$(document).ready(function() { 
    $('#table').DataTable( {
    fixedHeader: true
}); 
}); 

$('.edit').click(function(){
      var id              = $(this).closest("tr").find("td").eq(0).text();   
      var nip             = $(this).closest("tr").find("td").eq(1).text();   
      var nama            = $(this).closest("tr").find("td").eq(2).text();  
      var username        = $(this).closest("tr").find("td").eq(3).text();  
      var level           = $(this).closest("tr").find("td").eq(4).text();  
      var jenis_kelamin   = $(this).closest("tr").find("td").eq(5).text();  
      var Status          = $(this).closest("tr").find("td").eq(6).text();  
      $('#id').val(id); 
      $('#nama').val(nama);
      $('#nip').val(nip);
      $('#username').val(username);
      if(jenis_kelamin=='Laki-Laki'){
        $('#lk').prop('checked', true);
      }else{

        $('#pr').prop('checked', true);
      }
      $('#EditModal').modal('show');
      $('#level').val(level); 
    });

function update(){
              $('#EditModal').modal('hide');
              $('body').removeClass('modal-open');
              $('.modal-backdrop').remove();
           var id            = $('#id').val();  
           var username      = $('#username').val();      
           var nama_lengkap  = $('#nama').val();   
           var level         = $('#level').prop('selectedIndex')-1;      
           var nip           = $('#nip').val();   
           var gender        = $("input[name='gender']:checked").val();   

           $.ajax({url:"<?php echo site_url(); ?>/Data_user/update",
             method:"POST",
             data:{id:id,username:username,nama_lengkap:nama_lengkap,level:level,nip:nip,gender:gender},
             success:function(response){
              console.log(response);
               var json = JSON.parse(response);
                if(json.status){ 
                $('#username').val('');      
                $('#nama').val('');   
                $('#level').val('');      
                $('#nip').val('');   
                $('#content' ).empty();

                show_data();
                }

             },
             complete:function(){   

             }
             });
} 
</script>