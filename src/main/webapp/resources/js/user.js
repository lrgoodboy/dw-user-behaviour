var User = function(opts) {
    var self = this;
    self.contextPath = opts.contextPath;

    self.init();
};

User.prototype = {
    constructor: User,

    init: function() {
        var self = this;

        $('[name="date"]').datepicker({
            language: "zh-CN",
            format: "yyyy-mm-dd"
        });

        $('#btnFilter').click(function() {
            $('[name="page"]').val(1);
        });

        // pagination
        $('.pagination a').click(function() {
            $('[name="page"]').val($(this).attr('page'));
            $('#frmFilter').submit();
        });

        $('i[action-info]').popover({
            html: true,
            content: function() {
                var info = $(this).attr('action-info').split(',');
                return '系统：' + info[0] + '<br>'
                     + '版本：' + info[1] + '<br>'
                     + '渠道：' + info[2];
            }
        }).css('cursor', 'pointer');
    },

    _theEnd: undefined
};
