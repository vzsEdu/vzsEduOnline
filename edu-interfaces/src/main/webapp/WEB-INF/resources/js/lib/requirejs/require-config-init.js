require.config({
    baseUrl: '/resources/js',
    paths: {
        jquery: 'lib/jquery/jquery-1.11.1.min',
        handlebars: 'lib/handlebars/handlebars-v1.3.0.min'
    },
    shim: {
        handlebars: {
            exports: 'Handlebars'
        }
    }
});

require([
    'jquery',
    'handlebars'
]);