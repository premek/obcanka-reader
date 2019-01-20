#!/usr/bin/env bash
cd "$( dirname "${BASH_SOURCE[0]}" )"
OUT=$1
if [[ -z "$OUT" ]] ; then OUT=/dev/ttyS0 ; fi

java -jar obcanka-reader-all.jar | while read J; do 
  tmp=`tempfile`
  echo $J | jq -r 'keys[] as $k | "\($k)=\"\(.[$k])\" "' > $tmp
  echo "cat <<END" >> $tmp
  userconf=/boot/obcanka-reader-template.txt
  if [[ -f $userconf ]] ; then 
    cat $userconf >> $tmp
  else
    cat template >> $tmp
  fi
  echo "END" >> $tmp
  bash $tmp | iconv -f utf8 -t ascii//TRANSLIT > $OUT
  rm "$tmp"
done


