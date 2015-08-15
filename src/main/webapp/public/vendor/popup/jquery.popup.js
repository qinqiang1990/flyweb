(function($) {
    // 动画效果
    $.extend($.easing, {
        easeOutQuad: function(x, t, b, c, d) {
            return -c * (t /= d) * (t - 2) + b;
        }
    });
    var defaultParam = {
        clickOutsideHide: true,
        trigger: false,
        width: false,
        size: false, //'large' 900  'middle' 600  'small' 300
        background: false,
        padding: '20px',
        animate: true,
        duration: 800
    };
    var popId = 1;
    var template = {
        mask: '<div class="popup-mask" style="display:none;"></div>',
        popup: '<div  id="popup-{id}" class="popup-wrap popup-fade" style="display:none;">' + '<div class="popup-content">' + '</div>' + '</div>'
    };

    var $doc;
    var $body;

    function Popup($popup, param) {
        $doc = $(document);
        $body = $('body');
        var self = this;
        this.param = $.extend({}, defaultParam, param);
        param = this.param;
        if (!self.valid(param)) {
            return;
        }
        this.$mask = $('.popup-mask');
        if (this.$mask.length === 0) {
            $body.append($(template.mask));
            this.$mask = $('.popup-mask');
        }
        // 每个弹出层都一个包裹元素，目的是为了以后能支持弹出层上再弹出层
        this.$el = $(template.popup.replace('{id}', popId++));
        $body.append(this.$el);


        this.initView($popup); //外观

        $triggerElem = $(param.trigger);
        $triggerElem.addClass('trigger-popup-btn')


        $triggerElem.click(function() {
            self.show();
        });

        // 点外部，弹出框消失
        if (param.clickOutsideHide) {
            $(document).click(function(evt) {
                if (self.shouldHide(evt.target)) {
                    self.hide();
                }
            });
        } else {
            this.$el.addClass('click-outside-not-hide');
        }
    }

    function preventScroll(evt) {
        evt.stopPropagation();
        evt.preventDefault();
        evt.cancelBubble = false;
        return false;
    }


    $.extend(Popup.prototype, {
        sizeMap: {
            large: 900,
            middle: 600,
            small: 300
        },
        valid: function(param) {
            if (!param) {
                throw 'param needed';
            }
            if (!param.trigger) {
                throw 'trigger needed';
            }
            if (!param.width && !param.size) {
                throw 'width or size needed';
            }
            if (param.width && param.size) {
                throw 'one of width or size is enough';
            }
            return true;
        },
        initView: function($popup) {
            var param = this.param;
            var $el = this.$el;
            var $content = $el.find('.popup-content');
            $content.append($popup);

            if (param.padding) {
                $content.css('padding', param.padding);
            }

            if (param.size) {
                $el.addClass('popup-' + param.size);
            }

            if (param.height) {
                $content.height(param.height);
            }

            if (param.width) {
                $content.width(param.width);
            }

            var width = param.width ? param.width : this.sizeMap[param.size];
            var marginLeft = -(parseInt($content.css('padding-left'), 10) + parseInt($content.css('padding-right'), 10) + width) / 2;
            $content.css('margin-left', marginLeft);
            if (param.background) {
                $content.css('background', param.background);
            }
        },
        show: function() {
            var self = this;
            if (!-[1, ] && !window.XMLHttpRequest) { // 判断浏览器是否ie6
                this.$el.css('top', $doc.scrollTop())
                    .height($doc.height());
                this.$mask.css('top', $doc.scrollTop())
                    .height($doc.height());
            }
            var $popupContent = this.$el.find('.popup-content');
            $popupContent.css({
                top: -$popupContent.height()
            });
            this.$el.show();
            if (this.param.animate) {
                $popupContent.animate({
                    top: this.$el.height() * 0.2
                }, {
                    duration: this.param['duration'],
                    easing: 'easeOutQuad'
                });
            } else {
                $popupContent.css({
                    top: this.$el.height() * 0.2
                });
            }
            // return;
            this.$mask.show();
            $('body, html').css('overflow-y', 'hidden');
            $(document).on('mousewheel', preventScroll); // 非火狐
            $(document).on('DOMMouseScroll', preventScroll); // firefox
        },
        hide: function() {
            this.$el.hide();
            this.$mask.hide();
            $('body, html').css('overflow-y', '');
            $(document).off('mousewheel');
            $(document).off('DOMMouseScroll');
        },
        shouldHide: function(elem) {
            var $visibleWrap = $('.popup-wrap:visible');
            // 强制不关闭
            if($visibleWrap.hasClass('click-outside-not-hide')){
                return false;
            }
            // 点弹出框内部，则不关闭
            var toHide = false;
            var $wrap = $(elem).closest('.popup-content');
            if($wrap.length === 0 && !$(elem).hasClass('trigger-popup-btn')){
                toHide = true;
            }

            return toHide;
        }
    });

    $.fn.popup = function(param) {
        return new Popup(this, param);
    }
})(jQuery);
