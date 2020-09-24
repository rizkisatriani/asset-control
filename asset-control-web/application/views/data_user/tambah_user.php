 <div>
              <h4 class="card-title text-left">Input Data Asset</h4> 
               <p>Silahkan isi form pendaftaran user.</p>
      </div>
 <div class="row clearfix">
                <div class="col-lg-12">

                <div class="card col-lg-12">
                        <div class="header">               
                        </div>
                        <div class="body">

                <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">Nama Lengkap</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="nama" aria-describedby="emailHelp" placeholder="Tulis Nama User">
                </div>

                </div>   

               <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">NIP</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="nip" aria-describedby="emailHelp" placeholder="Tulis NIP User">
                </div>
              </div> 
              
               <div class="form-group row">
                        <label for="staticEmail" class="col-sm-3 col-form-label">Username</label>
                <div class="col-sm-9">
                        <input type="email" class="form-control" id="username" aria-describedby="emailHelp" placeholder="Tulis Username">
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
              <br>
              <br>
              <div class="col-sm-12 text-right">
               <button type="button" id="upload" class="btn   btn-outline-primary col-sm-2" onclick='insert()' data-dismiss="modal">Simpan</button>
                <button type="button" class="btn  btn-outline-danger col-sm-2" data-dismiss="modal">Batal</button>
              </div>
              </div>
              </div>


                    <div class="card col-lg-12">

                        <div class="body">
                          <table id="table" class="table table-hover js-basic-example dataTable table-custom spacing5">  
                                            <tr>
                                                <th class="mb-0">NIP</th>
                                                <th class="mb-0">Nama</th> 
                                                <th class="mb-0">Username</th>  
                                            </tr> 
                                </table> 
                        </div>
                    </div>
                </div>  
            </div>

 
<script  >
  function insert(){
           var username = $('#username').val();      
           var nama_lengkap  = $('#nama').val();   
           var level   = $('#level').val();      
           var nip    = $('#nip').val();   
           var gender     = $("input[name='gender']:checked").val();  
 
             $.ajax({url:"<?php echo site_url(); ?>/Data_user/insert",
             method:"POST",
             data:{username:username,nama_lengkap:nama_lengkap,level:level,nip:nip,gender:gender},
             success:function(response){
               var json = JSON.parse(response);
                if(json.status){
                $('#username').val('');      
                $('#nama').val('');   
                $('#level').val('');      
                $('#nip').val('');
                $('#table tr:last').after('<tr><th>'+nip+'</th><th>'+nama_lengkap+'</th><th>'+username+'</th></tr>');

                }  

             },
             complete:function(){   

             }
             });
}
</script>
 