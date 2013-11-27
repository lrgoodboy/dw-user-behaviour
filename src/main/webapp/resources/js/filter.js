var Filter = function(opts) {
    var self = this;
    self.contextPath = opts.contextPath;

    self.init();
};

Filter.prototype = {
    constructor: Filter,

    init: function() {
        var self = this;

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
