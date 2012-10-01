/**
 * Copyright 2012 - present by OpenGamma Inc. and the OpenGamma group of companies
 * Please see distribution for license.
 */
$.register_module({
    name: 'og.common.gadgets.Depgraph',
    dependencies: ['og.common.gadgets.manager'],
    obj: function () {
        var prefix = 'og_depgraph_gadget_', counter = 1;
        return function (config) {
            var gadget = this, alive = prefix + counter++, $selector = $(config.selector),
                css_position = {position: 'absolute', top: '0', left: 0, right: 0, bottom: 0}, grid;
            gadget.alive = function () {return !!$('.' + alive).length;};
            gadget.load = function () {
                $selector.addClass(alive).css(css_position);
                grid = new og.analytics.Grid({selector: config.selector, source: config.source});
            };
            gadget.load();
            gadget.resize = gadget.load;
            if (!config.child) og.common.gadgets.manager.register(gadget);
        };
    }
});