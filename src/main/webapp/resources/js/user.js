var User = function(opts) {
    var self = this;
    self.contextPath = opts.contextPath;

    self.init();
};

User.prototype = {
    constructor: User,

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
