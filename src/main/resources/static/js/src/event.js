var $startDateInput = $('#startDate');
var $endDateInput = $('#endDate');
var startDateTimePickerConfig = {};
var endDateTimePickerConfig = {};

if ($startDateInput.val()) {
    var startDate = moment($startDateInput.val());
    var formattedStartDate = startDate.format('yyyy-MM-DD HH:mm');
    $startDateInput.val(formattedStartDate);
    startDateTimePickerConfig.firstEmpty = false;
    startDateTimePickerConfig.selectData = formattedStartDate;
}
if ($endDateInput.val()) {
    var endDate = moment($endDateInput.val());
    var formattedEndDate = endDate.format('yyyy-MM-DD HH:mm');
    $endDateInput.val(formattedEndDate);
    endDateTimePickerConfig.firstEmpty = false;
    endDateTimePickerConfig.selectData = formattedEndDate;
}

var dateTimePickerSharedConfig = {
    locale: 'ru',
    title: 'Выберите дату и время',
    buttonTitle: 'Выбрать',
    firstEmpty: true
};

$('#startDatePicker').dateTimePicker({ ...dateTimePickerSharedConfig, ...startDateTimePickerConfig });
$('#endDatePicker').dateTimePicker({ ...dateTimePickerSharedConfig, ...endDateTimePickerConfig });

$('#add-member-btn').click(() => {
    var memberFieldHtml = `<div class="mb-3 input-group member-input">
                    <span class="input-group-text">@</span>
                    <input class="form-control" name="members[]" placeholder="Email участника...">
                    <button type="button" class="btn btn-danger remove-member">Удалить</button>
                </div>`;
    $('#member-list').append(memberFieldHtml);
});

$(document).on('click', '.remove-member', function () {
    $(this).closest('.member-input').remove();
});