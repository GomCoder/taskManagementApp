import Vue from 'vue'
import VueI18n from 'vue-i18n'
import { enUS, zhCN } from '@/locale'

Vue.use(VueI18n)
/**
 * VueI18n 옵션 인스턴스 생성
 * @type {VueI18n}
 */
export const i18n = new VueI18n({
  locale: 'en_US',
  messages: {
    en_US: enUS,
    zh_CN: zhCN
  }
})
