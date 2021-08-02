const dateTimePickerSharedConfig = {
    locale: 'ru',
    title: 'Выберите дату и время',
    buttonTitle: 'Выбрать',
    firstEmpty: true
};
$('#startDatePicker').dateTimePicker(dateTimePickerSharedConfig);
$('#endDatePicker').dateTimePicker(dateTimePickerSharedConfig);

$('#add-member-btn').click(() => {
    const memberFieldHtml = `<div class="mb-3 input-group member-input">
                    <span class="input-group-text">@</span>
                    <input type="email" class="form-control" name="members[]" placeholder="Email участника...">
                    <button type="button" class="btn btn-danger remove-member">Удалить</button>
                </div>`;
    $('#member-list').append(memberFieldHtml);
});

$(document).on('click', '.remove-member', function () {
    $(this).closest('.member-input').remove();
});