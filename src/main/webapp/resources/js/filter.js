var Filter = function(opts) {
    var self = this;
    self.contextPath = opts.contextPath;

    self.init();
};

Filter.prototype = {
    constructor: Filter,

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
    },

    _theEnd: undefined
};
