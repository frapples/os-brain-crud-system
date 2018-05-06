"use strict";

(function () {
    var apis = [
        {
            name: 'demoAPI',
            url: '/api/queryDemo',
            method: "POST",
        },
    ];

    var Network = function () {
        var self = {};
        self.apis = apis;

        var converter = function (response) {
            return new Promise(function(resolve, reject) {
                if (response.status == 200 && response.data.code == 200) {
                    resolve(response.data.data);
                } else {
                    (new Vue()).$notify.error({
                        title: 'Error',
                        message: 'System error, please contact the administrator'
                    });
                    reject();
                }
            });
        };

        var onError = function (error) {
            (new Vue()).$notify.error({
                title: 'Error',
                message: 'Network error'
            });
        };

        self.post = function (url, data) {
            var params = new URLSearchParams();
            for (var key in data) {
                params.append(key, data[key]);
            }

            return axios.post(url, params).then(converter).catch(onError);
        };

        self.put = function (url, data) {
            var params = new URLSearchParams();
            for (var key in data) {
                params.append(key, data[key]);
            }

            return axios.put(url, params).then(converter).catch(onError);
        };


        self.get = function (url, data) {
            return axios.get(url, {
                params: data,
            }).then(converter).catch(onError);
        };

        self.delete = function (url, data) {
            return axios.delete(url, {
                params: data,
            }).then(converter).catch(onError);
        };

        self.initApi = function () {
            self.api = {};
            self.apis.forEach(function (api) {
                self.api[api.name] = function (data) {
                    if (api.method == "POST") {
                        return self.post(api.url, data);
                    } else if (api.method == "GET") {
                        return self.post(api.url, data);
                    } else {
                        console.error("不知道对请求类型：" + api.method);
                    }
                };
            });
        };

        return self;
    };

    window.network = Network();
    window.network.initApi();

})();
