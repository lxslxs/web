!
function($) {
    "use strict";
    var BootstrapPaginator = function(element, options) {
        this.init(element, options)
    },
    old = null;
    BootstrapPaginator.prototype = {
        init: function(element, options) {
            this.$element = $(element);
            var version = options && options.bootstrapMajorVersion ? options.bootstrapMajorVersion: $.fn.bootstrapPaginator.defaults.bootstrapMajorVersion,
            id = this.$element.attr("id");
            if (2 === version && !this.$element.is("div")) throw "in Bootstrap version 2 the pagination must be a div element. Or if you are using Bootstrap pagination 3. Please specify it in bootstrapMajorVersion in the option";
            if (version > 2 && !this.$element.is("ul")) throw "in Bootstrap version 3 the pagination root item must be an ul element.";
            this.currentPage = 1,
            this.lastPage = 1,
            this.setOptions(options),
            this.initialized = !0
        },
        setOptions: function(options) {
            this.options = $.extend({},
            this.options || $.fn.bootstrapPaginator.defaults, options),
            this.totalPages = parseInt(this.options.totalPages, 10),
            this.numberOfPages = parseInt(this.options.numberOfPages, 10),
            options && "undefined" != typeof options.currentPage && this.setCurrentPage(options.currentPage),
            this.listen(),
            this.render(),
            this.initialized || this.lastPage === this.currentPage || this.$element.trigger("page-changed", [this.lastPage, this.currentPage])
        },
        listen: function() {
            this.$element.off("page-clicked"),
            this.$element.off("page-changed"),
            "function" == typeof this.options.onPageClicked && this.$element.bind("page-clicked", this.options.onPageClicked),
            "function" == typeof this.options.onPageChanged && this.$element.on("page-changed", this.options.onPageChanged),
            this.$element.bind("page-clicked", this.onPageClicked)
        },
        destroy: function() {
            this.$element.off("page-clicked"),
            this.$element.off("page-changed"),
            this.$element.removeData("bootstrapPaginator"),
            this.$element.empty()
        },
        show: function(page) {
            this.setCurrentPage(page),
            this.render(),
            this.lastPage !== this.currentPage && this.$element.trigger("page-changed", [this.lastPage, this.currentPage])
        },
        showNext: function() {
            var pages = this.getPages();
            pages.next && this.show(pages.next)
        },
        showPrevious: function() {
            var pages = this.getPages();
            pages.prev && this.show(pages.prev)
        },
        showFirst: function() {
            var pages = this.getPages();
            pages.first && this.show(pages.first)
        },
        showLast: function() {
            var pages = this.getPages();
            pages.last && this.show(pages.last)
        },
        onPageItemClicked: function(event) {
            var type = event.data.type,
            page = event.data.page;
            this.$element.trigger("page-clicked", [event, type, page])
        },
        onPageClicked: function(event, originalEvent, type, page) {
            var currentTarget = $(event.currentTarget);
            switch (type) {
            case "first":
                currentTarget.bootstrapPaginator("showFirst");
                break;
            case "prev":
                currentTarget.bootstrapPaginator("showPrevious");
                break;
            case "next":
                currentTarget.bootstrapPaginator("showNext");
                break;
            case "last":
                currentTarget.bootstrapPaginator("showLast");
                break;
            case "page":
                currentTarget.bootstrapPaginator("show", page)
            }
        },
        render: function() {
            var containerClass = this.getValueFromOption(this.options.containerClass, this.$element),
            size = this.options.size || "normal",
            alignment = this.options.alignment || "left",
            pages = this.getPages(),
            listContainer = 2 === this.options.bootstrapMajorVersion ? $("<ul></ul>") : this.$element,
            listContainerClass = 2 === this.options.bootstrapMajorVersion ? this.getValueFromOption(this.options.listContainerClass, listContainer) : null,
            first = null,
            prev = null,
            next = null,
            last = null,
            p = null,
            i = 0;
            switch (this.$element.prop("class", ""), this.$element.addClass("pagination"), size.toLowerCase()) {
            case "large":
            case "small":
            case "mini":
                this.$element.addClass($.fn.bootstrapPaginator.sizeArray[this.options.bootstrapMajorVersion][size.toLowerCase()])
            }
            if (2 === this.options.bootstrapMajorVersion) switch (alignment.toLowerCase()) {
            case "center":
                this.$element.addClass("pagination-centered");
                break;
            case "right":
                this.$element.addClass("pagination-right")
            }
            for (this.$element.addClass(containerClass), this.$element.empty(), 2 === this.options.bootstrapMajorVersion && (this.$element.append(listContainer), listContainer.addClass(listContainerClass)), this.pageRef = [], pages.first && (first = this.buildPageItem("first", pages.first), first && listContainer.append(first)), pages.prev && (prev = this.buildPageItem("prev", pages.prev), prev && listContainer.append(prev)), i = 0; i < pages.length; i += 1) p = this.buildPageItem("page", pages[i]),
            p && listContainer.append(p);
            pages.next && (next = this.buildPageItem("next", pages.next), next && listContainer.append(next)),
            pages.last && (last = this.buildPageItem("last", pages.last), last && listContainer.append(last))
        },
        buildPageItem: function(type, page) {
            var itemContainer = $("<li></li>"),
            itemContent = $("<a></a>"),
            text = "",
            title = "",
            itemContainerClass = this.options.itemContainerClass(type, page, this.currentPage),
            itemContentClass = this.getValueFromOption(this.options.itemContentClass, type, page, this.currentPage),
            tooltipOpts = null;
            switch (type) {
            case "first":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) return;
                text = this.options.itemTexts(type, page, this.currentPage),
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "last":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) return;
                text = this.options.itemTexts(type, page, this.currentPage),
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "prev":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) return;
                text = this.options.itemTexts(type, page, this.currentPage),
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "next":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) return;
                text = this.options.itemTexts(type, page, this.currentPage),
                title = this.options.tooltipTitles(type, page, this.currentPage);
                break;
            case "page":
                if (!this.getValueFromOption(this.options.shouldShowPage, type, page, this.currentPage)) return;
                text = this.options.itemTexts(type, page, this.currentPage),
                title = this.options.tooltipTitles(type, page, this.currentPage)
            }
            return itemContainer.addClass(itemContainerClass).append(itemContent),
            itemContent.addClass(itemContentClass).html(text).on("click", null, {
                type: type,
                page: page
            },
            $.proxy(this.onPageItemClicked, this)),
            this.options.pageUrl && itemContent.attr("href", this.getValueFromOption(this.options.pageUrl, type, page, this.currentPage)),
            this.options.useBootstrapTooltip ? (tooltipOpts = $.extend({},
            this.options.bootstrapTooltipOptions, {
                title: title
            }), itemContent.tooltip(tooltipOpts)) : itemContent.attr("title", title),
            itemContainer
        },
        setCurrentPage: function(page) {
            if (page > this.totalPages || 1 > page) throw "Page out of range";
            this.lastPage = this.currentPage,
            this.currentPage = parseInt(page, 10)
        },
        getPages: function() {
            var totalPages = this.totalPages,
            pageStart = 0 === this.currentPage % this.numberOfPages ? (parseInt(this.currentPage / this.numberOfPages, 10) - 1) * this.numberOfPages + 1: parseInt(this.currentPage / this.numberOfPages, 10) * this.numberOfPages + 1,
            output = [],
            i = 0,
            counter = 0;
            for (pageStart = 1 > pageStart ? 1: pageStart, i = pageStart, counter = 0; counter < this.numberOfPages && totalPages >= i; i += 1, counter += 1) output.push(i);
            return output.first = 1,
            output.prev = this.currentPage > 1 ? this.currentPage - 1: 1,
            output.next = this.currentPage < totalPages ? this.currentPage + 1: totalPages,
            output.last = totalPages,
            output.current = this.currentPage,
            output.total = totalPages,
            output.numberOfPages = this.options.numberOfPages,
            output
        },
        getValueFromOption: function(value) {
            var output = null,
            args = Array.prototype.slice.call(arguments, 1);
            return output = "function" == typeof value ? value.apply(this, args) : value
        }
    },
    old = $.fn.bootstrapPaginator,
    $.fn.bootstrapPaginator = function(option) {
        var args = arguments,
        result = null;
        return $(this).each(function(index, item) {
            var $this = $(item),
            data = $this.data("bootstrapPaginator"),
            options = "object" != typeof option ? null: option;
            if (!data) return data = new BootstrapPaginator(this, options),
            $this = $(data.$element),
            $this.data("bootstrapPaginator", data),
            void 0;
            if ("string" == typeof option) {
                if (!data[option]) throw "Method " + option + " does not exist";
                result = data[option].apply(data, Array.prototype.slice.call(args, 1))
            } else result = data.setOptions(option)
        }),
        result
    },
    $.fn.bootstrapPaginator.sizeArray = {
        2: {
            large: "pagination-large",
            small: "pagination-small",
            mini: "pagination-mini"
        },
        3: {
            large: "pagination-lg",
            small: "pagination-sm",
            mini: ""
        }
    },
    $.fn.bootstrapPaginator.defaults = {
        containerClass: "",
        size: "normal",
        alignment: "left",
        bootstrapMajorVersion: 2,
        listContainerClass: "",
        itemContainerClass: function(type, page, current) {
            return page === current ? "active": ""
        },
        itemContentClass: function(type, page, current) {
            return ""
        },
        currentPage: 1,
        numberOfPages: 5,
        totalPages: 1,
        pageUrl: function(type, page, current) {
            return null
        },
        onPageClicked: null,
        onPageChanged: null,
        useBootstrapTooltip: !1,
        shouldShowPage: function(type, page, current) {
            var result = !0;
            switch (type) {
            case "first":
                result = 1 !== current;
                break;
            case "prev":
                result = 1 !== current;
                break;
            case "next":
                result = current !== this.totalPages;
                break;
            case "last":
                result = current !== this.totalPages;
                break;
            case "page":
                result = !0
            }
            return result
        },
        itemTexts: function(type, page, current) {
            switch (type) {
            case "first":
                return "&lt;&lt;";
            case "prev":
                return "&lt;";
            case "next":
                return "&gt;";
            case "last":
                return "&gt;&gt;";
            case "page":
                return page
            }
        },
        tooltipTitles: function(type, page, current) {
            switch (type) {
            case "first":
                return "第一页";
            case "prev":
                return "上一页";
            case "next":
                return "下一页";
            case "last":
                return "最后一页";
            case "page":
                return page === current ? "当前页  " + page: "显示第 " + page+" 页 "
            }
        },
        bootstrapTooltipOptions: {
            animation: !0,
            html: !0,
            placement: "top",
            selector: !1,
            title: "",
            container: !1
        }
    },
    $.fn.bootstrapPaginator.Constructor = BootstrapPaginator
} (window.jQuery);