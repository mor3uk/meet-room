$(document).ready(function () {
    var calendarEl = document.getElementById('calendar');
    var calendar = new FullCalendar.Calendar(calendarEl, {
        initialView: 'timeGridWeek',
        locale: 'ru',
        eventContent: function (info) {
            var contentContainer = document.createElement('div');
            var title = info.event.title;
            var members = info.event.extendedProps.members;
            var membersHtml = '';

            if (members) {
                membersHtml += '<ul>';
                members.forEach(member => {
                    membersHtml += '<li>' + member + '</li>';
                });
                membersHtml += '</ul>';
            }

            contentContainer.innerHTML = '<a>' + title + '</a>' + membersHtml;
            return { html: contentContainer.innerHTML };
        },
        buttonText: {
            today: window.app.const.today
        },
        allDaySlot: false,
        events: loadEvents,
        customButtons: {
            addEventButton: {
                text: window.app.const.addEvent,
                click: function () {
                    location.replace('/event/add');
                }
            }
        },
        headerToolbar: {
            center: 'addEventButton',
            right: 'prev,next'
        },
    });

    function loadEvents(info, successCallback) {
        $.ajax('/event/list', {
            data: {
                'startDate': moment(info.start).format('yyyy-MM-DD HH:mm'),
                'endDate': moment(info.end).format('yyyy-MM-DD HH:mm')
            },
            success(events) {
                if (Array.isArray(events)) {
                    if (calendar) {
                        calendar.removeAllEvents();
                    }
                    events = events.map(event => {
                        event.start = event.startDate;
                        event.end = event.endDate;
                        event.eventId = event.id;
                        return event;
                    });
                }
                successCallback(events);
            }
        });
    }

    calendar.render();
    calendar.on('next', () => {
        calendar.removeAllEvents();
    });
    calendar.on('prev', () => {
        calendar.removeAllEvents();
    });
});
