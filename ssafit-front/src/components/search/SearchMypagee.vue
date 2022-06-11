<template>
  <div class="pa-5">
    <table class="table table-responsive">
    <thead>
        <tr>
            <th scope="col" v-for="day in days" :key="day">{{ day }}</th> //요일 출력
        </tr>
    </thead>
    <tbody>
        <tr v-for="(weeks, FirstIdx) in dates" :key="FirstIdx">
            <td scope="row" v-for="(date, SecondIdx) in weeks" :key="SecondIdx">
                <div>{{ date }}</div>
            </td>
        </tr>
    </tbody>
    </table>
  </div>
</template>

<script>
export default {
    data() {
        return {
            dateOpen: false,
            start: '',
            type: 'month',
            typeOptions: [
                {text: 'Day', value: 'day'},
                {text: 'Week', value: 'week'},
                {text: 'Month', value: 'month'},
            ],
        }
    },
    created() {
     this.year = this.today.getFullYear();
     this.month = this.today.getMonth();
     this.date = this.today.getDate();
     this.getDates(); // 달력의 전체 날짜를 출력하는 함수
    },
    methods: {
        getFirstAndLastDate(month, year){
                    const lastMonthLastDate = new Date(year, month, 0).getDate();
                    const lastMonthLastDay = new Date(year, month, 0).getDay();
                    const thisMonthLastDate = new Date(year, month+1, 0).getDate();
                    const nextMonthFirstDay = new Date(year,month+1).getDay();
                    return [this.lastMonthLastDate=lastMonthLastDate, this.lastMonthLastDay=lastMonthLastDay, 
                    this.thisMonthLastDate=thisMonthLastDate, this.nextMonthFirstDay=nextMonthFirstDay];
        },
        getDates(){
    	const [lastMonthLastDate, lastMonthLastDay, thisMonthLastDate, nextMonthFirstDay] = 
            this.getFirstAndLastDate(this.currentMonth, this.currentYear);
        },
        getPrevMonth(prevLastDate, prevLastDay){
            if(prevLastDay!==6){
                for(let date = prevLastDate-prevLastDay; date <= prevLastDate; date++){
                    this.week.push(date);
                    this.checkLength();
                }
            }
        },
        getThisMonth(thisMonthLastDate){
                for(let date = 1; date<=thisMonthLastDate; date++){
                    this.week.push(date);
                    this.checkLength();
                }
        },
        getNextMonth(nextMonthFirstDay){
            if(nextMonthFirstDay!==0){
                for(let date = 1 ; date <= 7-nextMonthFirstDay; date++){
                    this.week.push(date);
                    this.checkLength();
                }
           }
        },
    }
}
</script>