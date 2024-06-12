// 첨부파일 이미지 뷰
   ( /* att_zone : 이미지들이 들어갈 위치 id, btn : file tag id */

        imageView = function imageView(att_zone, btn){
            var attZone = document.getElementById(att_zone);
            var btnAtt = document.getElementById(btn);
            var page= document.getElementById(page);
            var sel_files = [];
// 이미지와 체크 박스를 감싸고 있는 div 속성
            var div_style = 'display:inline-block;position:relative; padding:0;'
                + 'width:150px;height:120px;margin:5px;border:none ;z-index:1';
// 미리보기 이미지 속성
            var img_style = 'width:100%;height:100%;z-index:none;border-radius: 10px';
// 이미지안에 표시되는 체크박스의 속성
            var chk_style = 'width:30px;height:30px;position:absolute;font-size:20px;border:none;'
                + 'right:0px;bottom:0px;z-index:50;background-color:rgba(255,255,255,0.1);color:#f00';
            var icon_style = 'width:30px;height:14px;position:absolute;z-index:49;'+'right:0px;bottom:0px;background-color:rgba(255,255,255,0.1);color:#f00';
            //input박스 변경 감지시 이미지표시
            btnAtt.onchange = function(e){
                var files = e.target.files;
                var fileArr = Array.prototype.slice.call(files)
                for(f of fileArr){
                    imageLoader(f);
                }
            }


// 탐색기에서 드래그앤 드롭 사용
            attZone.addEventListener('dragenter', function(e){
                e.preventDefault();
                e.stopPropagation();
            }, false)
            attZone.addEventListener('dragleave', function(e){
                 e.preventDefault();
                 e.stopPropagation();
                $(e.target).css({
                   "background-color": "#BFACE2",
                    "outline-offset": "-2px"
                });
            },false)

            attZone.addEventListener('dragover', function(e){
                e.preventDefault();
                e.stopPropagation();
                $(e.target).css({
                  "background-color": "#6F61C0",
                  "outline-offset": "-10px"
                });



            }, false)
            attZone.addEventListener('drop', function(e){
                 $(e.target).css({
                    "background-color": "#BFACE2",
                    "outline-offset": "-2px"
                 });
                var files = {};
                e.preventDefault();
                e.stopPropagation();
                var dt = e.dataTransfer;
                files = dt.files;
                for(f of files){
                    imageLoader(f);
                }
            }, false)
            attZone.addEventListener('click', function(e){
                e.preventDefault();
                e.stopPropagation();
                $('#btnAtt').click();
            }, false)

            /*첨부된 이미지를 배열에 넣고 미리보기 */
            imageLoader = function(file){
                sel_files.push(file);
                var reader = new FileReader();
                reader.onload = function(ee){
                    let img = document.createElement('img')
                    img.setAttribute('style', img_style)
                    img.src = ee.target.result;
                    attZone.appendChild(makeDiv(img, file));
                }
                reader.readAsDataURL(file);
            }




            /*첨부된 파일이 있는 경우 checkbox와 함께 attZone에 추가할 div를 만들어 반환 */
            makeDiv = function(img, file){
                var div = document.createElement('div');

                div.setAttribute('style', div_style);
                var btn = document.createElement('input');
                btn.setAttribute('type', 'button');
                btn.setAttribute('delFile', file.name);
                btn.setAttribute('style', chk_style);
                var icon = document.createElement('i');
                icon.setAttribute('class','fa-solid fa-square-minus fa-2xl');
                icon.setAttribute('style', icon_style);


                btn.onclick = function(ev){
                    ev.preventDefault();
                    ev.stopPropagation();
                    var ele = ev.srcElement;
                    var delFile = ele.getAttribute('delFile');
                    for(var i=0 ;i<sel_files.length; i++){
                        if(delFile== sel_files[i].name){
                            sel_files.splice(i, 1);
                        }
                    }
                    dt = new DataTransfer();
                    for(f in sel_files) {
                        var file = sel_files[f];
                        dt.items.add(file);
                    }
                    btnAtt.files = dt.files;
                    var p = ele.parentNode;
                    attZone.removeChild(p)
                }
                div.appendChild(img);
                div.appendChild(btn);
                div.appendChild(icon);
                div.setAttribute('class', 'col');
                attZone.appendChild(div);

                return div;
            }
        }
        )('att_zone', 'btnAtt')