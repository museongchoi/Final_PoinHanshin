$(document).ready(function() {
        map_date();
})

function map_date(){
        var mapDate = $('#missingtime');
        mapDate.datetimepicker({
            lang: 'ko',
            format: 'Y-m-d H:i',
            ininne:true,
            scrollMonth: false,
            scrollInput: false
        });

}