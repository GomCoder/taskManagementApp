(function(){"use strict";var e={3988:function(e,t,r){var s=r(144),a=function(){var e=this,t=e._self._c;return t("div",{attrs:{id:"app"}},[t("router-view")],1)},i=[],o={name:"App"},n=o,l=r(1001),m=(0,l.Z)(n,a,i,!1,null,null,null),c=m.exports,d=r(8345),u=function(){var e=this;e._self._c;return e._m(0)},f=[function(){var e=this,t=e._self._c;return t("div",[t("h1",[e._v("TaskAgile")])])}],p={name:"LoginPage"},v=p,h=(0,l.Z)(v,u,f,!1,null,null,null),g=h.exports,C=function(){var e=this,t=e._self._c;return t("div",{staticClass:"container"},[t("div",{staticClass:"row justify-content-center"},[t("div",{staticClass:"register-form"},[e._m(0),t("form",{on:{submit:function(t){return t.preventDefault(),e.submitForm.apply(null,arguments)}}},[t("div",{directives:[{name:"show",rawName:"v-show",value:e.errorMessage,expression:"errorMessage"}],staticClass:"alert alert-danger failed"},[e._v(e._s(e.errorMessage))]),t("div",{staticClass:"form-group"},[t("label",{attrs:{for:"username"}},[e._v("Username")]),t("input",{directives:[{name:"model",rawName:"v-model",value:e.form.username,expression:"form.username"}],staticClass:"form-control",attrs:{type:"text",id:"username"},domProps:{value:e.form.username},on:{input:function(t){t.target.composing||e.$set(e.form,"username",t.target.value)}}}),e.$v.form.username.$dirty?t("div",{staticClass:"field-error"},[e.$v.form.username.required?e._e():t("div",{staticClass:"error"},[e._v("Username is required")]),e.$v.form.username.alphaNum?e._e():t("div",{staticClass:"error"},[e._v("Username can only contain letters and numbers")]),e.$v.form.username.minLength?e._e():t("div",{staticClass:"error"},[e._v("Username must have at least "+e._s(e.$v.form.username.$params.minLength.min)+" letters.")]),e.$v.form.username.maxLength?e._e():t("div",{staticClass:"error"},[e._v("Username is too long. It can contains maximium "+e._s(e.$v.form.username.$params.maxLength.max)+" letters.")])]):e._e()]),t("div",{staticClass:"form-group"},[t("label",{attrs:{for:"emailAddress"}},[e._v("Email address")]),t("input",{directives:[{name:"model",rawName:"v-model",value:e.form.emailAddress,expression:"form.emailAddress"}],staticClass:"form-control",attrs:{type:"email",id:"emailAddress"},domProps:{value:e.form.emailAddress},on:{input:function(t){t.target.composing||e.$set(e.form,"emailAddress",t.target.value)}}}),e.$v.form.emailAddress.$dirty?t("div",{staticClass:"field-error"},[e.$v.form.emailAddress.required?e._e():t("div",{staticClass:"error"},[e._v("Email address is required")]),e.$v.form.emailAddress.email?e._e():t("div",{staticClass:"error"},[e._v("This is not a valid email address")]),e.$v.form.emailAddress.maxLength?e._e():t("div",{staticClass:"error"},[e._v("Email address is too long. It can contains maximium "+e._s(e.$v.form.emailAddress.$params.maxLength.max)+" letters.")])]):e._e()]),t("div",{staticClass:"form-group"},[t("label",{attrs:{for:"password"}},[e._v("Password")]),t("input",{directives:[{name:"model",rawName:"v-model",value:e.form.password,expression:"form.password"}],staticClass:"form-control",attrs:{type:"password","id-":"password"},domProps:{value:e.form.password},on:{input:function(t){t.target.composing||e.$set(e.form,"password",t.target.value)}}}),e.$v.form.password.$dirty?t("div",{staticClass:"field-error"},[e.$v.form.password.required?e._e():t("div",{staticClass:"error"},[e._v("Password is required")]),e.$v.form.password.minLength?e._e():t("div",{staticClass:"error"},[e._v("Password is too short. It can contains at least "+e._s(e.$v.form.password.$params.minLength.min)+" letters.")]),e.$v.form.password.maxLength?e._e():t("div",{staticClass:"error"},[e._v("Password is too long. It can contains maximium "+e._s(e.$v.form.password.$params.maxLength.max)+" letters.")])]):e._e()]),t("button",{staticClass:"btn btn-primary btn-block",attrs:{type:"submit"}},[e._v("Create account")]),e._m(1),e._m(2)])])]),e._m(3)])},A=[function(){var e=this,t=e._self._c;return t("div",{staticClass:"logo-wrapper"},[t("img",{staticClass:"logo",attrs:{src:r(6338),alt:"로고 이미지"}}),t("div",{staticClass:"tagline"},[e._v("Open source task management tool")])])},function(){var e=this,t=e._self._c;return t("p",{staticClass:"accept-terms text-muted"},[e._v("By clicking “Create account”, you agree to our "),t("a",{attrs:{href:"#"}},[e._v("terms of service")]),e._v(" and "),t("a",{attrs:{href:"#"}},[e._v("privacy policy")]),e._v(".")])},function(){var e=this,t=e._self._c;return t("p",{staticClass:"text-center text-muted"},[e._v("Already have an account? "),t("a",{attrs:{href:"/login"}},[e._v("Sign in")])])},function(){var e=this,t=e._self._c;return t("footer",{staticClass:"footer"},[t("span",{staticClass:"copyright"},[e._v("© 2018 TaskAgile.com")]),t("ul",{staticClass:"footer-links list-inline float-right"},[t("li",{staticClass:"list-inline-item"},[t("a",{attrs:{href:"#"}},[e._v("About")])]),t("li",{staticClass:"list-inline-item"},[t("a",{attrs:{href:"#"}},[e._v("Terms of Service")])]),t("li",{staticClass:"list-inline-item"},[t("a",{attrs:{href:"#"}},[e._v("Privacy Policy")])]),t("li",{staticClass:"list-inline-item"},[t("a",{attrs:{href:"https://github.com/taskagile/vuejs.spring-boot.mysql",target:"_blank"}},[e._v("GitHub")])])])])}],I=(r(7658),r(379)),E=r(9423),Z={register(e){return new Promise(((t,r)=>{E.Z.post("/registrations",e).then((({data:e})=>{t(e)})).catch((e=>{r(e)}))}))}},Q={name:"RegisterPage",data:function(){return{form:{username:"",emailAddress:"",password:""},errorMessage:""}},validations:{form:{username:{required:I.C1,minLength:(0,I.Ei)(2),maxLength:(0,I.BS)(50),alphaNum:I.Pf},emailAddress:{required:I.C1,email:I.Do,maxLength:(0,I.BS)(100)},password:{required:I.C1,minLength:(0,I.Ei)(6),maxLength:(0,I.BS)(30)}}},methods:{submitForm(){this.$v.$touch(),this.$v.$invalid||Z.register(this.form).then((()=>{this.$router.push({name:"LoginPage"})})).catch((e=>{this.errorMessage="Failed to register user. "+e.message}))}}},R=Q,S=(0,l.Z)(R,C,A,!1,null,"6804e7fc",null),x=S.exports;s.ZP.use(d.ZP);const k=[{path:"/login",name:"LoginPage",component:g},{path:"/register",name:"RegisterPage",component:x}],w=new d.ZP({mode:"history",base:"/",routes:k});var J=w,y=r(629);s.ZP.use(y.ZP);var B=new y.ZP.Store({state:{},getters:{},mutations:{},actions:{},modules:{}}),b=r(8620);E.Z.defaults.baseURL="/api",E.Z.defaults.headers.common.Accept="application/json",E.Z.interceptors.response.use((e=>e),(e=>Promise.reject(e))),s.ZP.use(b.ZP),s.ZP.config.productionTip=!1,new s.ZP({router:J,store:B,render:e=>e(c)}).$mount("#app")},6338:function(e){e.exports="data:image/png;base64,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"}},t={};function r(s){var a=t[s];if(void 0!==a)return a.exports;var i=t[s]={exports:{}};return e[s].call(i.exports,i,i.exports,r),i.exports}r.m=e,function(){var e=[];r.O=function(t,s,a,i){if(!s){var o=1/0;for(c=0;c<e.length;c++){s=e[c][0],a=e[c][1],i=e[c][2];for(var n=!0,l=0;l<s.length;l++)(!1&i||o>=i)&&Object.keys(r.O).every((function(e){return r.O[e](s[l])}))?s.splice(l--,1):(n=!1,i<o&&(o=i));if(n){e.splice(c--,1);var m=a();void 0!==m&&(t=m)}}return t}i=i||0;for(var c=e.length;c>0&&e[c-1][2]>i;c--)e[c]=e[c-1];e[c]=[s,a,i]}}(),function(){r.d=function(e,t){for(var s in t)r.o(t,s)&&!r.o(e,s)&&Object.defineProperty(e,s,{enumerable:!0,get:t[s]})}}(),function(){r.g=function(){if("object"===typeof globalThis)return globalThis;try{return this||new Function("return this")()}catch(e){if("object"===typeof window)return window}}()}(),function(){r.o=function(e,t){return Object.prototype.hasOwnProperty.call(e,t)}}(),function(){r.j=143}(),function(){var e={143:0};r.O.j=function(t){return 0===e[t]};var t=function(t,s){var a,i,o=s[0],n=s[1],l=s[2],m=0;if(o.some((function(t){return 0!==e[t]}))){for(a in n)r.o(n,a)&&(r.m[a]=n[a]);if(l)var c=l(r)}for(t&&t(s);m<o.length;m++)i=o[m],r.o(e,i)&&e[i]&&e[i][0](),e[i]=0;return r.O(c)},s=self["webpackChunkfront_end"]=self["webpackChunkfront_end"]||[];s.forEach(t.bind(null,0)),s.push=t.bind(null,s.push.bind(s))}();var s=r.O(void 0,[998],(function(){return r(3988)}));s=r.O(s)})();
//# sourceMappingURL=app.a8ed6409.js.map