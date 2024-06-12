 //date-time-picker-range
    $(document).ready(function() {
        range_date();
        range_date2();
    })
    function range_date(){
        var startDate = $('#startDate');
        var endDate = $('#endDate');
        startDate.datetimepicker({
            lang: 'ko',
            format: 'Y-m-d H:i',
            scrollMonth: false,
            scrollInput: false,
            onShow: function (ct) {
                this.setOptions({
                    maxDate: endDate.val() ? endDate.val() : false
                })
            },
        });
        endDate.datetimepicker({
            lang: 'ko',
            format: 'Y-m-d H:i',
            scrollMonth: false,
            scrollInput: false,
            onShow: function (ct) {
                this.setOptions({
                    minDate: startDate.val() ? startDate.val() : false
                })
            }
        });
    }
    function range_date2(){
        var startDate = $('#startDate2');
        var endDate = $('#endDate2');
        startDate.datetimepicker({
            lang: 'ko',
            format: 'Y-m-d H:i:s',
            scrollMonth: false,
            scrollInput: false,
            onShow: function (ct) {
                this.setOptions({
                    maxDate: endDate.val() ? endDate.val() : false
                })
            },
        });
        endDate.datetimepicker({
            lang: 'ko',
            format: 'Y-m-d H:i:s',
            scrollMonth: false,
            scrollInput: false,
            onShow: function (ct) {
                this.setOptions({
                    minDate: startDate.val() ? startDate.val() : false
                })
            }
        });
    }

    $.datetimepicker.setLocale('ko');