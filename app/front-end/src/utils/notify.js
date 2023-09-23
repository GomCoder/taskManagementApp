import Noty from 'noty'
import 'noty/lib/noty.css'
import 'noty/lib/themes/relax.css'
const showError = function (errorMessage) {
  new Noty({
    type: 'error',
    theme: 'relax',
    closeWith: ['click', 'button'],
    text: errorMessage
  }).show()
}

// const closeAll = function () {
//   Noty.closeAll()
// }

export default {
  error: showError
  // error: showError,
  // closeAll: closeAll
}
